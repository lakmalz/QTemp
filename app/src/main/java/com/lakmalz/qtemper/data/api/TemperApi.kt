package com.lakmalz.qtemper.data.api

import com.lakmalz.qtemper.data.api.response.JobResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TemperApi {

    /**
     * Interest and distance set as a hardcode value
     * Date passing as a dynamic date value {date format > yyyy-MM-dd }
     */
    @GET("contractor/shifts?interests=5,7,8,9,10,12,13,14,19,21,23,24,26&distance=%7B\"range\":50,\"geolocation\":%7B\"lat\":\"52.360537\",\"lng\":\"4.993292\"%7D%7D")
    fun getJobList(@Query("dates") date: String): Observable<JobResponse>
}