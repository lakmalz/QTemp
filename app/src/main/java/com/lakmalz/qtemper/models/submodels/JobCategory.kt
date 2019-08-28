package com.lakmalz.qtemper.models.submodels


import com.google.gson.annotations.SerializedName

class JobCategory(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon_path")
    val iconPath: String,
    @SerializedName("slug")
    val slug: String
)