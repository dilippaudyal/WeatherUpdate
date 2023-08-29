package com.code.challenge.wateher.weatherupdate.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.code.challenge.wateher.weatherupdate.data.model.ErrorResponse
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.*

fun convertDateTime(epoch: Long): String {

    val sdf = SimpleDateFormat("dd-yyyy HH:mm")
    return sdf.format(Date(epoch))
}


fun AppCompatActivity.genericMessageDialog(
    dialogActionTitle: String,
    message: String,
    handlerUserAction: (Boolean) -> Unit
) {

    var genericMessageDialog: AlertDialog? = null
    if (genericMessageDialog == null)
        genericMessageDialog = showGenericMessageDialog {
            cancelable = false
            isBackGroundTransparent = false
            okayClickListener(dialogActionTitle, message) {
                handlerUserAction(true)
            }
        }
    genericMessageDialog?.show()

}

fun AppCompatActivity.locationAddDialog(
    getCityName: (String?) -> Unit
) {

    var genericMessageDialog: AlertDialog? = null
    if (genericMessageDialog == null)
        genericMessageDialog = showLocationAddDialog {
            cancelable = false
            isBackGroundTransparent = false
            okayClickListener {
                getCityName(it)
            }

            cancelClickListener()
        }
    genericMessageDialog?.show()

}

fun Context.showGenericMessageDialog(func: GenericMessageDialog.() -> Unit): AlertDialog? {
    return GenericMessageDialog(this).apply {
        func()
    }.create()
}

fun Context.showLocationAddDialog(func: LocationAddDialog.() -> Unit): AlertDialog? {
    return LocationAddDialog(this).apply {
        func()
    }.create()
}

fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}