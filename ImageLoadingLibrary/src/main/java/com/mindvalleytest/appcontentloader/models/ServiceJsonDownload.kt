package com.mindvalleytest.appcontentloader.models

import com.mindvalleytest.appcontentloader.callback.ContentServiceObserver
import com.google.gson.Gson

import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

class ServiceJsonDownload(url: String, contentServiceObserver: ContentServiceObserver) : ServiceContentTypeDownload(url, ContentDataType.JSON, contentServiceObserver) {

    private val jsonAsString: String
        get() {
            try {
                return String(data!!, StandardCharsets.UTF_8)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

    override fun getCopyOfMe(contentServiceObserver: ContentServiceObserver): ServiceContentTypeDownload {
        return ServiceJsonDownload(this.url, contentServiceObserver)
    }

    fun getJson(type: Type): Any {
        val gson = Gson()
        return gson.fromJson(jsonAsString, type)
    }
}
