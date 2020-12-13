package com.udacity.asteroidradar.data.sources.remote

import com.udacity.asteroidradar.data.sources.remote.netwokmodels.NetworkAsteroid
import com.udacity.asteroidradar.data.sources.remote.netwokmodels.PictureFromNetwork
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay


/**
 * Created By Varsha Kulkarni on 30/11/20
 */
fun List<Asteroid>.toNetworkModel():List<NetworkAsteroid>{

    return map{
        NetworkAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun List<NetworkAsteroid>.toDomainModel():List<Asteroid>{
    return map{
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun PictureFromNetwork.toDomainModel(): PictureOfDay {
    return PictureOfDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}

fun PictureOfDay.toDomainModel(): PictureFromNetwork {
    return PictureFromNetwork(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}