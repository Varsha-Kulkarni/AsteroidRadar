package com.udacity.asteroidradar.data.sources.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created By Varsha Kulkarni on 30/11/20
 */
@Entity(tableName = "picture_table")
@Parcelize
data class PictureEntity(@PrimaryKey(autoGenerate = true) val id: Long=0L,
                          val mediaType: String, val title: String, val url: String) : Parcelable