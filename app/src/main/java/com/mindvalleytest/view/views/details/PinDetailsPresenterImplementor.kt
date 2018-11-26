package com.mindvalleytest.view.views.details

import android.annotation.SuppressLint

import com.mindvalleytest.model.CategoryDetails
import com.mindvalleytest.model.MasterDetails
import java.lang.String.format

/**
 * Pin details presenter implementation.
 * @author Sandeep D
 */
internal class PinDetailsPresenterImplementor(private val detailsView: PinDetailsView?) : PinDetailsPresenter {

    override fun renderDetails(currentData: MasterDetails) {
        if (detailsView != null) {
            if (currentData != null) {
                detailsView.renderPhoto(currentData.urlDetails!!.full!!)
                detailsView.setBackgroundColor(currentData.color!!)
                detailsView.renderUserDetails(currentData.user!!)
                detailsView.renderPhotoDetails(currentData)
                detailsView.renderCategories(getDisplayCategories(currentData.categories))
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun getDisplayCategories(categories: List<CategoryDetails>?): String {
        var displayCategories = ""
        for (categoriesDetailsResponse in categories!!) {
//            if (displayCategories.trim() == "") {
//                displayCategories = format("%s (%d)", categoriesDetailsResponse.title, categoriesDetailsResponse.photoCount)
//            } else {
                displayCategories = format("%s/%s", displayCategories, format("%s (%d)", categoriesDetailsResponse.title, categoriesDetailsResponse.photoCount))
//            }
        }
        return displayCategories
    }
}
