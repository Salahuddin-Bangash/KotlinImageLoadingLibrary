package com.mindvalleytest.appcontentloader.models

import com.mindvalleytest.appcontentloader.callback.ContentServiceObserver

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

abstract class ServiceContentTypeDownload protected constructor(val url: String, val contentDataType: ContentDataType, val contentServiceObserver: ContentServiceObserver) {
    var data: ByteArray? = null
    val keyMD5: String
    // User For Just For Test
    var comeFrom = "Internet"

    init {
        this.keyMD5 = md5(this.url)
    }

    abstract fun getCopyOfMe(contentServiceObserver: ContentServiceObserver): ServiceContentTypeDownload

    companion object {

        fun md5(s: String): String {
            try {
                // Create MD5 Hash
                val digest = MessageDigest
                        .getInstance("MD5")
                digest.update(s.toByteArray())
                val messageDigest = digest.digest()
                // Create Hex String
                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) {
                        h = "0" + h
                    }
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return ""
        }
    }
}
