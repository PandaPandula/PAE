package com.sagalogistics.utils.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.sagalogistics.R
import kotlinx.coroutines.NonCancellable.cancel

class OrdersDialog(val a : Int, val b : Int, val c : Int, private val callbackCanceled: () -> Unit) : DialogFragment() {


    var apretado = 0


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(a)
                .setPositiveButton(b,
                    DialogInterface.OnClickListener { dialog, id ->

                            callbackCanceled()

                    })
                .setNegativeButton(c,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}