package com.udacity.asteroidradar.data.sources.local

import com.udacity.asteroidradar.data.sources.local.entities.AsteroidEntity
import com.udacity.asteroidradar.data.sources.local.entities.PictureEntity
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay


/**
 * Created By Varsha Kulkarni on 30/11/20
 */
fun ArrayList<Asteroid>.toDatabaseModel():Array<AsteroidEntity>{

    return map{
        AsteroidEntity(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}

fun List<AsteroidEntity>.toDomainModel():List<Asteroid>{
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

fun PictureOfDay.toDatabaseModel():PictureEntity{
    return PictureEntity(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}

fun PictureEntity.toDomainModel():PictureOfDay{
    return PictureOfDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}

