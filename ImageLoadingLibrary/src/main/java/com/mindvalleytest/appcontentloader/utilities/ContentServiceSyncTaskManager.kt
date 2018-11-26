package com.mindvalleytest.appcontentloader.utilities

import com.mindvalleytest.appcontentloader.callback.ContentServiceStatusObserver
import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler

import cz.msebera.android.httpclient.Header

internal class ContentServiceSyncTaskManager {
    operator fun get(serviceContentTypeDownload: ServiceContentTypeDownload, contentServiceStatusObserver: ContentServiceStatusObserver): AsyncHttpClient {
        val client = AsyncHttpClient(true, 80, 443)
        client.get(serviceContentTypeDownload.url, object : AsyncHttpResponseHandler() {
            override fun onStart() {
                serviceContentTypeDownload.contentServiceObserver.onStart(serviceContentTypeDownload)
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: ByteArray) {
                // called when response HTTP status is "200 OK"
                serviceContentTypeDownload.data = response
                serviceContentTypeDownload.contentServiceObserver.onSuccess(serviceContentTypeDownload)
                // Successfull response from server
                contentServiceStatusObserver.setDone(serviceContentTypeDownload)
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, errorResponse: ByteArray, e: Throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                serviceContentTypeDownload.contentServiceObserver.onFailure(serviceContentTypeDownload, statusCode, errorResponse, e)
                // Failure Response from server
                contentServiceStatusObserver.onFailure(serviceContentTypeDownload)
            }

            override fun onRetry(retryNo: Int) {
                // Retry the failed request
                serviceContentTypeDownload.contentServiceObserver.onRetry(serviceContentTypeDownload, retryNo)
            }

            override fun onCancel() {
                super.onCancel()
                // Cancel the request
                contentServiceStatusObserver.setCancelled(serviceContentTypeDownload)
            }
        })
        return client
    }
}


