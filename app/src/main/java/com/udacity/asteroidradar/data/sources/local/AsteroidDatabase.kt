package com.udacity.asteroidradar.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.data.sources.local.dao.AsteroidDao
import com.udacity.asteroidradar.data.sources.local.dao.PictureDao
import com.udacity.asteroidradar.data.sources.local.entities.AsteroidEntity
import com.udacity.asteroidradar.data.sources.local.entities.PictureEntity


/**
 * Created By Varsha Kulkarni on 30/11/20
 */

@Database(entities = [AsteroidEntity::class, PictureEntity::class], version = 1)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
    abstract val pictureDao: PictureDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AsteroidDatabase::class.java,
                "asteroids").build()
        }
    }
    return INSTANCE
}