package com.lakmalz.qtemper.utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.lakmalz.qtemper.models.Job
import com.lakmalz.qtemper.models.submodels.Data
import java.lang.reflect.Type

class JobsDeserializer : JsonDeserializer<Data> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): Data {
        val jsonObject = element.asJsonObject

        var jobs = ArrayList<Job>()

        for ((s, value) in jsonObject.entrySet()) {
            jobs =  context.deserialize<ArrayList<Job>>((value as JsonArray), object : TypeToken<ArrayList<Job>>() {}.type)
        }
        return Data(jobs)
    }

}