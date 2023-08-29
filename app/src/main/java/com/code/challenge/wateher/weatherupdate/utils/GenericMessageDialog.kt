package com.code.challenge.wateher.weatherupdate.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.code.challenge.wateher.weatherupdate.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView

class GenericMessageDialog(context: Context) : BaseDialogHelper() {

    //  dialog view
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_generic_message, null)
    }

    override val builder: MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context).setView(dialogView)

    private val tvOkay: MaterialTextView by lazy {
        dialogView.findViewById(R.id.tv_okay)
    }

    private val tvDialogMessage: MaterialTextView by lazy {
        dialogView.findViewById(R.id.tvDialogMessage)
    }

    fun okayClickListener(dialogActionTitle: String, message: String, func: (() -> Unit)? = null) =
        with(tvOkay) {
            tvOkay.text = dialogActionTitle
            tvDialogMessage.text = message
            setClickListenerToDialogIcon(func)
        }

    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
}