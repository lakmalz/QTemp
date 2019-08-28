package com.lakmalz.qtemper.models


import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("api_version")
    val apiVersion: Meta.ApiVersion? = null

    inner class ApiVersion(
        @SerializedName("current")
        val current: Double,
        @SerializedName("deprecation_date")
        val deprecationDate: String,
        @SerializedName("latest")
        val latest: Double,
        @SerializedName("released")
        val released: String
    )
}