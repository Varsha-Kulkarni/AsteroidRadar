package com.udacity.asteroidradar.data.sources.remote.netwokmodels

import com.squareup.moshi.Json


/**
 * Created By Varsha Kulkarni on 30/11/20
 */
data class PictureFromNetwork(@Json(name = "media_type") val mediaType: String, val title: String, val url: String)