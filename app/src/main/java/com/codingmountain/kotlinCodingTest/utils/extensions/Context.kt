package com.codingmountain.kotlincodingtest.utils.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast


fun Context.dpToPx(dp: Int) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
        .toInt()

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
