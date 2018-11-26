package com.mindvalleytest.appcontentloader.utilities

import android.util.LruCache

import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload

class ContentServiceCachingManager private constructor() {
    private val maxCacheSize: Int
    private val mDownloadDataTypeCache: LruCache<String, ServiceContentTypeDownload>?

    init {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        // Use 1/8th of the available memory for this memory cache.
        maxCacheSize = maxMemory / 8 // 4 * 1024 * 1024; // 4MiB
        mDownloadDataTypeCache = LruCache(maxCacheSize)
    }

    fun getMDownloadDataType(key: String): ServiceContentTypeDownload? {
        return mDownloadDataTypeCache?.get(key)
    }

    fun putMDownloadDataType(key: String, serviceContentTypeDownload: ServiceContentTypeDownload): Boolean {
        return mDownloadDataTypeCache?.put(key, serviceContentTypeDownload) != null
    }

    fun clearTheCash() {
        mDownloadDataTypeCache?.evictAll()
    }

    companion object {
        @Volatile private var INSTANCE: ContentServiceCachingManager? = null

//        fun getInstance(): ContentServiceCachingManager =
//                ContentServiceCachingManager.INSTANCE ?: synchronized(this) {
//                    if (ContentServiceCachingManager.INSTANCE == null) {
//                        ContentServiceCachingManager.INSTANCE = ContentServiceCachingManager()
//                    }
//                    return ContentServiceCachingManager.INSTANCE as ContentServiceCachingManager
//                }
        private var instance: ContentServiceCachingManager? = null

        fun getInstance(): ContentServiceCachingManager {
            if (instance == null) {
                instance = ContentServiceCachingManager()
            }
            return instance as ContentServiceCachingManager
        }
    }
}
