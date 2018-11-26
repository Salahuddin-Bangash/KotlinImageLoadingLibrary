package com.mindvalleytest.utility

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

import com.mindvalleytest.view.views.common.DialogListener

/**
 * Alert dialog shown using this class.s
 *
 * @author Salahuddin
 */

object AlertUtil {
    private var alertDialogBuilder: AlertDialog.Builder? = null
    private var alertDialog: AlertDialog? = null

    /**
     * This function is used to display the Alert Dialog Box (Multiple Option) to the User.
     *
     *
     * This function also handles the operations users can perform on the Alert Dialog.
     *
     * @param currentContext - The Context
     * @param dialogID       - The Unique Dialog ID for current screen.
     * @param dialogListener - The Dialog Event Listener
     * @param params         - The Alert Dialog Display Parameters (Heading,Detail Message,Action Texts)
     */
    fun showAlertDialogMultipleOptions(currentContext: Context, dialogID: Int, dialogListener: DialogListener?, vararg params: String) {
        alertDialogBuilder = AlertDialog.Builder(currentContext)
        alertDialogBuilder!!.setTitle(params[0])
        alertDialogBuilder!!.setMessage(params[1])
        alertDialogBuilder!!.setCancelable(false)
        alertDialogBuilder!!.setPositiveButton(params[2]) { dialog, which ->
            dialogListener?.onPositiveAction(dialogID, null)
        }
        alertDialogBuilder!!.setNegativeButton(params[3]) { dialog, which ->
            dialogListener?.onNegativeAction(dialogID, null)
        }
        alertDialog = alertDialogBuilder!!.create()
        alertDialog!!.show()
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}
