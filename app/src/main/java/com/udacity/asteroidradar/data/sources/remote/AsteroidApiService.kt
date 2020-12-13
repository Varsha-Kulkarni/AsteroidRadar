package com.udacity.asteroidradar.data.sources.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.data.sources.remote.netwokmodels.PictureFromNetwork
import kotlinx.coroutines.Deferred
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
    fun getAsteroids()

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key")apiKey:String = API_KEY):Deferred<PictureFromNetwork>
}

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL).build()


object Network{
    val asteroidService = retrofit.create(AsteroidApiService::class.java)
}