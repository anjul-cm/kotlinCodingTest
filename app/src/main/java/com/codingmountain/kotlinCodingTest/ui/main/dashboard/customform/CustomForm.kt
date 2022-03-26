/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/26/22, 7:05 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.main.dashboard.customform

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import com.codingmountain.kotlincodingtest.R
import com.codingmountain.kotlincodingtest.utils.extensions.dpToPx

open class CustomForm : LinearLayout {
    constructor(context: Context) : super(context)
    private constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    private constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private var editText: EditText? = null

    fun initView(formInfo: FormInfo): CustomForm {
        orientation = VERTICAL
        setPadding(0, context.dpToPx(4), 0, context.dpToPx(4))
        addView(getTextViewForTitle(formInfo.title))
        editText = when (formInfo.formType) {
            FormType.STRING -> getEditTextForString()
            FormType.NUMBER -> getEditTextForNumber()
        }
        addView(editText)
        return this
    }

    private fun getEditTextForNumber(): EditText {
        return getNormalEditText().apply {
            inputType =
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        }
    }

    private fun getEditTextForString(): EditText {
        return getNormalEditText().apply {
            inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    private fun getNormalEditText(): EditText {
        return EditText(context).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, context.dpToPx(4), 0, 0)
            }
            setPadding(context.dpToPx(12))
            typeface = ResourcesCompat.getFont(context, R.font.bevietnamproregular)
            background = ContextCompat.getDrawable(context, R.drawable.edittext_background)
            setTextColor(ContextCompat.getColor(context, R.color.gray_900))
        }
    }

    private fun getTextViewForTitle(title: String): TextView {
        return TextView(context).apply {
            text = title
            typeface = ResourcesCompat.getFont(context, R.font.bevietnamprolight)
            setTextColor(ContextCompat.getColor(context, R.color.gray_500))
        }
    }

    fun setValue(text: String) {
        editText?.setText(text)
    }

    fun getFormValue(): String = editText?.text.toString()
}

enum class FormType {
    STRING,
    NUMBER
}

data class FormInfo(
    val formType: FormType,
    val title: String
)
