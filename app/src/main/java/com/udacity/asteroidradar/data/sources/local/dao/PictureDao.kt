package com.udacity.asteroidradar.data.sources.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.sources.local.entities.PictureEntity


/**
 * Created By Varsha Kulkarni on 29/11/20
 */
@Dao
interface PictureDao{
    @Query("select * from picture_table")
    fun getPictureOfTheDay() : LiveData<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pictureEntity: PictureEntity)

    @Query("Delete from picture_table")
    fun  clear()
}