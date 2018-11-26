package com.mindvalleytest.appcontentloader.callback

import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload

interface ContentServiceObserver {
    fun onStart(serviceContentTypeDownload: ServiceContentTypeDownload)

    fun onSuccess(serviceContentTypeDownload: ServiceContentTypeDownload)

    fun onFailure(serviceContentTypeDownload: ServiceContentTypeDownload, statusCode: Int, errorResponse: ByteArray, e: Throwable)

    fun onRetry(serviceContentTypeDownload: ServiceContentTypeDownload, retryNo: Int)
}
