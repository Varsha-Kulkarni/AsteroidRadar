package com.udacity.asteroidradar.data.sources.remote.netwokmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created By Varsha Kulkarni on 30/11/20
 */
@Parcelize
data class NetworkAsteroid(val id: Long, val codename: String, val closeApproachDate: String,
                           val absoluteMagnitude: Double, val estimatedDiameter: Double,
                           val relativeVelocity: Double, val distanceFromEarth: Double,
                           val isPotentiallyHazardous: Boolean) : Parcelable