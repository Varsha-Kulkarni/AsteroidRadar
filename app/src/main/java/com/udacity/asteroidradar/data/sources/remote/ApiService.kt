package com.udacity.asteroidradar.data.sources.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig.API_KEY
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.data.sources.remote.netwokmodels.PictureFromNetwork
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created By Varsha Kulkarni on 29/11/20
 */
interface AsteroidApiService{

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("start_date")startDate:String,
                     @Query("end_date")endDate:String,
                     @Query("api_key")apiKey: String = API_KEY): String
}

interface PictureOfTheDayService{

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key")apiKey:String = API_KEY): PictureFromNetwork
}
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory( MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL).build()


object Network{
    val asteroidService = retrofit.create(AsteroidApiService::class.java)
    val pictureOfTheDayService = retrofit.create(PictureOfTheDayService::class.java)
}
