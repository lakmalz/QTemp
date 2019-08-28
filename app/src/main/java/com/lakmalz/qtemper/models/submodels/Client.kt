package com.lakmalz.qtemper.models.submodels

import com.google.gson.annotations.SerializedName


class Client{
    @SerializedName("factoring_allowed")
    val factoringAllowed: Boolean = false
    @SerializedName("factoring_payment_term_in_days")
    val factoringPaymentTermInDays: Float = 0f
    @SerializedName("id")
    val id: String = ""
    @SerializedName("name")
    val name: String = ""
    @SerializedName("photos")
    val photos: List<Photo> = emptyList()
}