package com.lakmalz.qtemper.models.submodels


import com.google.gson.annotations.SerializedName

class Date(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
)