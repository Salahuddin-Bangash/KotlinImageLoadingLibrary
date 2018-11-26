package com.mindvalleytest.view.views.common

/**
 * This interface is used to handle the Actions performed by the User on Alert Dialog Box
 *
 * @author Salahuddin
 * @version 1.0
 */

interface DialogListener {

    /**
     * This function is called when the Positive Action in performed on the Alert Dialog Box
     *
     * @param dialogID    -The Current Dialog ID
     * @param updatedData - The Data after the Option is Selected by user
     */
    fun onPositiveAction(dialogID: Int, updatedData: Any?)

    /**
     * This function is called when the Negative Action in performed on the Alert Dialog Box
     *
     * @param dialogID    -The Current Dialog ID
     * @param updatedData - The Data after the Option is Selected by user
     */
    fun onNegativeAction(dialogID: Int, updatedData: Any?)
}
