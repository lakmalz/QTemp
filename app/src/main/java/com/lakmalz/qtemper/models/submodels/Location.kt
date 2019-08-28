package com.lakmalz.qtemper.models.submodels


import com.google.gson.annotations.SerializedName

class Location(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)