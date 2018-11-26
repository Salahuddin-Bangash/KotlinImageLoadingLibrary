package com.mindvalleytest.appcontentloader.callback

import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload

interface ContentServiceStatusObserver {
    fun setDone(serviceContentTypeDownload: ServiceContentTypeDownload)

    fun setCancelled(serviceContentTypeDownload: ServiceContentTypeDownload)

    fun onFailure(serviceContentTypeDownload: ServiceContentTypeDownload)
}
