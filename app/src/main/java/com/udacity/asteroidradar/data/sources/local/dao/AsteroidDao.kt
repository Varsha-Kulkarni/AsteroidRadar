package com.udacity.asteroidradar.data.sources.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.sources.local.entities.AsteroidEntity


/**
 * Created By Varsha Kulkarni on 29/11/20
 */
@Dao
interface AsteroidDao {
    @Query("select * from asteroid_table")
    fun getAsteroids(): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidEntity)

    @Query("delete from asteroid_table")
    fun clear()
}