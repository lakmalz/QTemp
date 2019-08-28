package com.lakmalz.qtemper.models.submodels


import com.google.gson.annotations.SerializedName

class Shift(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("duration")
    val duration: Float,
    @SerializedName("earnings_per_hour")
    val earningsPerHour: Float,
    @SerializedName("end_datetime")
    val endDatetime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("is_auto_accepted_when_applied_for")
    val isAutoAcceptedWhenAppliedFor: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("start_datetime")
    val startDatetime: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("tempers_needed")
    val tempersNeeded: Int
)