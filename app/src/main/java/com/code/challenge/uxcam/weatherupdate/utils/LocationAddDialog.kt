package com.code.challenge.uxcam.weatherupdate.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.code.challenge.uxcam.weatherupdate.R
import com.code.challenge.uxcam.weatherupdate.main.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import javax.sql.StatementEvent

class LocationAddDialog(context: Context) : BaseDialogHelper() {

    //  dialog view
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_location_add, null)
    }

    override val builder: MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context).setView(dialogView)

    private val tvOkay: MaterialTextView by lazy {
        dialogView.findViewById(R.id.tv_okay)
    }

    private val tvCancel: MaterialTextView by lazy {
        dialogView.findViewById(R.id.tv_cancel)
    }

    private val cityName: TextInputEditText by lazy {
        dialogView.findViewById(R.id.et_city_name)
    }


    fun okayClickListener(func: ((cityName: String?) -> Unit)? = null) =
        with(tvOkay) {
            setClickListenerToDialogIcon(func)
        }

    fun cancelClickListener() =
        with(tvCancel) {
            clickCancel()
        }


    private fun View.setClickListenerToDialogIcon(func: ((cityName: String?) -> Unit)?) =
        setOnClickListener {
            val cityName = cityName.text.toString()
            if (cityName.isNotEmpty()) {
                func?.invoke(cityName)
                dialog?.dismiss()
            } else {
                Toast.makeText(context, "Please enter city name", Toast.LENGTH_SHORT).show()
            }
        }

    private fun View.clickCancel() =
        setOnClickListener {
            dialog?.dismiss()
        }

}