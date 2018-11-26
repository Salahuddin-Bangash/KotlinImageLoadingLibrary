package com.mindvalleytest.view.views.common

/**
 * This interface is used to implement the click listeners throughout the application.
 *
 * @author Salahuddin
 * @version 1.0
 */
interface ItemActionListener {
    /**
     * This function is used to handle the List Item Click event.
     *
     * @param currentObject
     * -The current List Clicked Object
     * @param position-
     * The current Index
     */
    fun onItemClicked(currentObject: Any, position: Int)

}
