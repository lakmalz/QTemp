package com.lakmalz.qtemper.models.submodels


import com.google.gson.annotations.SerializedName

data class Format(
    @SerializedName("cdn_url")
    val cdnUrl: String
)