package com.mindvalleytest.view.views.details

import com.mindvalleytest.model.MasterDetails
import com.mindvalleytest.model.UserDetails

/**
 * This interface is used for pin detail view activities.
 *
 * @author Salahuddin
 * @version 1.0
 */
internal interface PinDetailsView {

    fun renderPhoto(full: String)

    fun setBackgroundColor(color: String)

    fun renderUserDetails(user: UserDetails)

    fun renderPhotoDetails(currentData: MasterDetails)

    fun renderCategories(displayCategories: String)
}
