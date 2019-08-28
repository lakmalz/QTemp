package com.lakmalz.qtemper.models.submodels


import com.google.gson.annotations.SerializedName


class Photo(
    @SerializedName("formats")
    val formats: List<Format>
)