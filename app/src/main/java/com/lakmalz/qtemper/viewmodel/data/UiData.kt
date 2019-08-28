package com.lakmalz.qtemper.viewmodel.data

import com.lakmalz.qtemper.data.api.response.JobResponse

data class UiData(val jobList: JobResponse?, val message: String, val error: Throwable? = null)