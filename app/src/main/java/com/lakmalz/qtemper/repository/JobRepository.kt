package com.lakmalz.qtemper.repository

import com.lakmalz.qtemper.data.api.TemperApi
import com.lakmalz.qtemper.data.api.response.JobResponse
import io.reactivex.Observable

class JobRepository(private val temperApi: TemperApi) {

    fun getJobList(date: String): Observable<JobResponse>{
        return temperApi.getJobList(date)
            .doOnNext{

            }
    }
}