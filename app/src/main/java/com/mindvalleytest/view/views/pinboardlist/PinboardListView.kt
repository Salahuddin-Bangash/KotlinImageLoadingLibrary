package com.mindvalleytest.view.views.pinboardlist

import com.mindvalleytest.model.MasterDetails
import com.mindvalleytest.view.views.common.CommonView

import java.util.ArrayList

/**
 * This interface is used for pinboard activities.
 *
 * @author Salahuddin
 * @version 1.0
 */
internal interface PinboardListView : CommonView {

    fun renderUserList(users: ArrayList<MasterDetails>)
}
