package com.mindvalleytest.view.views.pinboardlist

import android.content.Context
import android.os.Handler

import com.google.gson.Gson
import com.mindvalleytest.R
import com.mindvalleytest.appcontentloader.callback.ContentServiceObserver
import com.mindvalleytest.appcontentloader.models.ServiceContentTypeDownload
import com.mindvalleytest.appcontentloader.models.ServiceJsonDownload
import com.mindvalleytest.appcontentloader.utilities.ContentTypeServiceDownload
import com.mindvalleytest.model.MasterDetails
import com.mindvalleytest.utility.AlertUtil
import com.mindvalleytest.utility.Constants
import com.mindvalleytest.utility.NetworkUtil

import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.Collections

/**
 * This class is used for pinboard list operations.
 *
 * @author Salahuddin
 * @version 1.0
 */
internal class PinboardListPresenterImplementor(private val context: Context, private val listingView: PinboardListView?) : PinboardListPresenter {
    private val mProvider: ContentTypeServiceDownload
    private val users: ArrayList<MasterDetails>

    init {
        mProvider = ContentTypeServiceDownload.getInstance()
        users = ArrayList()
    }

    override fun fetchUsers() {
        if (NetworkUtil.isAirplaneModeWithNoWIFI(context)) {
            listingView!!.displayErrorMessage(context.getString(R.string.network_error))
            return
        } else if (!NetworkUtil.isNetworkAvailable(context)) {
            listingView!!.displayErrorMessage(context.getString(R.string.network_error))
            return
        }

        fetchUsersFromServer()
    }

    override fun clearCache() {
        mProvider.clearTheCash()
        AlertUtil.showToast(context, context.getString(R.string.str_cache_cleared))
    }

    private fun fetchUsersFromServer() {

        val mDataTypeJson = ServiceJsonDownload(Constants.API_URL, object : ContentServiceObserver {
            override fun onStart(mDownloadDataType: ServiceContentTypeDownload) {

            }

            override fun onSuccess(mDownloadDataType: ServiceContentTypeDownload) {
                val response = String(mDownloadDataType.data!!, StandardCharsets.UTF_8)
                val detailsResponses = Gson().fromJson<Array<MasterDetails>>(response, Array<MasterDetails>::class.java!!)

                if (detailsResponses.size != 0) {
                    users.clear()
                    Collections.addAll(users, *detailsResponses)
                }
                val handler = Handler()
                handler.postDelayed({
                    listingView?.renderUserList(users)
                }, context.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())

            }

            override fun onFailure(mDownloadDataType: ServiceContentTypeDownload, statusCode: Int, errorResponse: ByteArray, e: Throwable) {}

            override fun onRetry(mDownloadDataType: ServiceContentTypeDownload, retryNo: Int) {

            }
        })
        mProvider.getRequest(mDataTypeJson)
    }
}
