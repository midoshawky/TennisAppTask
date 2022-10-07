package com.shawky.zimozitennisapptask.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TennisPlayer(
    val id : Int,
    val name:String,
    val country:String,
    val city:String,
    @SerializedName("imgURL")
    val img:String?
) : Parcelable
