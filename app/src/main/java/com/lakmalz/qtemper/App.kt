package com.lakmalz.qtemper

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lakmalz.qtemper.data.api.TemperApi
import com.lakmalz.qtemper.models.submodels.Data
import com.lakmalz.qtemper.repository.JobRepository
import com.lakmalz.qtemper.utils.Constant
import com.lakmalz.qtemper.utils.JobsDeserializer
import com.lakmalz.qtemper.viewmodel.JobListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application()  {

    companion object{
        private lateinit var retrofit: Retrofit
        private lateinit var temperApi: TemperApi
        private lateinit var JobRepository: JobRepository
        private lateinit var jobViewModel: JobListViewModel

        fun injectJobViewModel() = jobViewModel

        fun injectTemperApi() = temperApi
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()

        temperApi = retrofit.create(TemperApi::class.java)

        JobRepository = JobRepository(temperApi)

        jobViewModel = JobListViewModel(JobRepository)
    }

    /**
     * get Gson object and set custom deserializer
     */
    private fun getGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Data::class.java, JobsDeserializer())
        return gsonBuilder.create()
    }
}