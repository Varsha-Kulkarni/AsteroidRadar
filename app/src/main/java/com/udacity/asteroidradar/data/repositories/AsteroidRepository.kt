package com.udacity.asteroidradar.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.data.sources.local.AsteroidDatabase
import com.udacity.asteroidradar.data.sources.local.toDatabaseModel
import com.udacity.asteroidradar.data.sources.local.toDomainModel
import com.udacity.asteroidradar.data.sources.remote.Network
import com.udacity.asteroidradar.data.sources.remote.getOneWeekAheadDateFormatted
import com.udacity.asteroidradar.data.sources.remote.getTodayDateFormatted
import com.udacity.asteroidradar.data.sources.remote.parseAsteroidsJsonResult
import com.udacity.asteroidradar.domain.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber
import java.time.LocalDate


/**
 * Created By Varsha Kulkarni on 29/11/20
 */
class AsteroidRepository (private val database: AsteroidDatabase)
{
    enum class FilterType {WEEKLY, TODAY, SAVED}

    private val _filterType =
        MutableLiveData(FilterType.WEEKLY)
    val filterType : LiveData<FilterType>
        get() = _filterType

    @RequiresApi(Build.VERSION_CODES.O)
    private val _startDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val _endDate = _startDate.plusDays(7)

    @RequiresApi(Build.VERSION_CODES.O)
    val asteroids : LiveData<List<Asteroid>> = Transformations.switchMap(filterType)
    {
        when(it){
            FilterType.WEEKLY ->
                Transformations.map(database.asteroidDao.getWeeklyAsteroids(_startDate.toString(), _endDate.toString())){
                    it.toDomainModel()
                }

            FilterType.TODAY ->
                Transformations.map(database.asteroidDao.getTodaysAsteroids(_startDate.toString())){
                    it.toDomainModel()
                }

            FilterType.SAVED ->
                Transformations.map(database.asteroidDao.getSavedAsteroids()){
                    it.toDomainModel()
                }

            else -> throw IllegalArgumentException("")
        }
    }

    fun applyFilter(filterType: FilterType){
        _filterType.value = filterType
    }

    suspend fun refreshAsteroids(){
        withContext(Dispatchers.IO){
            try{
                val startDate = getTodayDateFormatted()
                val endDate = getOneWeekAheadDateFormatted()

                val asteroidsResult  = Network.asteroidService.getAsteroids(
                    startDate , endDate)
                val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroidsResult))

                database.asteroidDao.insertAll(*parsedAsteroids.toDatabaseModel())
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    Timber.d("Refresh failed ${e.message}")
                }
                e.printStackTrace()

            }

        }
    }

    suspend fun removeOldAsteroids(){
        withContext(Dispatchers.IO){
            database.asteroidDao.removeOldAsteroids(getTodayDateFormatted())
        }
    }

}