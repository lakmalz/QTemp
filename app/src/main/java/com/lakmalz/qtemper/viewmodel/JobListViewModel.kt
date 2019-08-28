package com.lakmalz.qtemper.viewmodel

import com.lakmalz.qtemper.repository.JobRepository
import com.lakmalz.qtemper.viewmodel.data.UiData
import io.reactivex.Observable

class JobListViewModel(private val jobRepository: JobRepository) {

    fun getJobList(date: String) : Observable<UiData>{
        return jobRepository.getJobList(date)
            .map {
                UiData(it, "Successfully downloaded.")
            }.onErrorReturn {
                UiData(null, "An error occurred.", it)
            }
    }
}