package com.lakmalz.qtemper.models


import com.google.gson.annotations.SerializedName
import com.lakmalz.qtemper.models.submodels.Client
import com.lakmalz.qtemper.models.submodels.JobCategory
import com.lakmalz.qtemper.models.submodels.Shift

class Job(){

    @SerializedName("allows_factoring")
    val allowsFactoring: Boolean = false
    @SerializedName("client")
    val client: Client? = null
    @SerializedName("distance")
    val distance: Int = 0
    /*@SerializedName("date")
    val date: Date? = null*/
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("job_category")
    val jobCategory: JobCategory? = null
    @SerializedName("key")
    val key: String = ""
    @SerializedName("max_possible_earnings_hour")
    val maxPossibleEarningsHour: Float = 0f
    @SerializedName("max_possible_earnings_total")
    val maxPossibleEarningsTotal: Float = 0f
    @SerializedName("new_matches_count")
    val newMatchesCount: Int = 0
    @SerializedName("open_positions")
    val openPositions: Int = 0
    @SerializedName("shifts")
    val shifts: List<Shift>? = null
    @SerializedName("title")
    val title: String = ""
    @SerializedName("url")
    val url: String = ""


}