package com.lakmalz.qtemper.data.api.response

import com.google.gson.annotations.SerializedName
import com.lakmalz.qtemper.models.Meta
import com.lakmalz.qtemper.models.submodels.Data

data class JobResponse(
    @SerializedName("data") val data: Data,
    @SerializedName("meta") val meta: Meta
)