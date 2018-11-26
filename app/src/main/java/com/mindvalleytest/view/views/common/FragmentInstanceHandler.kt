package com.mindvalleytest.view.views.common

import android.app.Fragment

/**
 * This interface is used as callback for fragment switch
 *
 * @author Salahuddin
 * @version 1.0
 */
interface FragmentInstanceHandler {
    /**
     * This function is used to handle the fragment operations.
     * @param fromFragment - current fragment
     * @param toFragment - The Fragment to be added/updated
     * @param addToBackStack  - Whether to add to back-stack or not
     */
    fun changeFragment(fromFragment: Fragment?, toFragment: Fragment, addToBackStack: Boolean)
}
