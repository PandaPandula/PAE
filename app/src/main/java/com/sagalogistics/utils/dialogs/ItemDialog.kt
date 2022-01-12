package com.sagalogistics.utils.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.sagalogistics.R
import kotlinx.coroutines.NonCancellable.cancel

class ItemDialog(val a : Int, val c : Int, val b : Int) : DialogFragment() {

    val isDone = MutableLiveData(false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(a)
                .setPositiveButton(b,
                    DialogInterface.OnClickListener { dialog, id ->
                        isDone.postValue(true)
                    })
                .setNegativeButton(c,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        requireActivity().finish()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}