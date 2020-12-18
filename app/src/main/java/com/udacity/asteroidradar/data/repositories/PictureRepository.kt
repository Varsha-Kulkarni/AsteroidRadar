package com.udacity.asteroidradar.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.data.sources.local.AsteroidDatabase
import com.udacity.asteroidradar.data.sources.local.toDatabaseModel
import com.udacity.asteroidradar.data.sources.local.toDomainModel
import com.udacity.asteroidradar.data.sources.remote.Network
import com.udacity.asteroidradar.data.sources.remote.toDomainModel
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


/**
 * Created By Varsha Kulkarni on 29/11/20
 */

class PictureRepository(private val database: AsteroidDatabase) {

    val pictureOfTheDay: LiveData<PictureOfDay> =
        Transformations.map(database.pictureDao.getPictureOfTheDay()) {
            it?.toDomainModel()
        }

    suspend fun refreshPictureOfTheDay() {
        withContext(Dispatchers.IO) {
            try {
                val picture = Network.pictureOfTheDayService.getPictureOfTheDay()
                val domainPicture = picture.toDomainModel()
                Timber.i("picture  = $domainPicture")
                if (domainPicture.mediaType == "image") {
                    database.pictureDao.clear()
                    database.pictureDao.insertAll(domainPicture.toDatabaseModel())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Timber.d("Refresh failed ${e.message}")
                }
                e.printStackTrace()

            }
        }
    }
}