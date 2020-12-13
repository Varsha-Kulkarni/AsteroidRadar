package com.udacity.asteroidradar.data.sources.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created By Varsha Kulkarni on 29/11/20
 */
@Entity(tableName = "asteroid_table")
@Parcelize
data class AsteroidEntity(@PrimaryKey(autoGenerate = true) val id: Long, val codename: String, val closeApproachDate: String,
                          val absoluteMagnitude: Double, val estimatedDiameter: Double,
                          val relativeVelocity: Double, val distanceFromEarth: Double,
                          val isPotentiallyHazardous: Boolean) : Parcelable