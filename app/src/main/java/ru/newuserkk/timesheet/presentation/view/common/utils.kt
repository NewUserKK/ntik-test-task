package ru.newuserkk.timesheet.presentation.view.common

import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar

fun EditText.setTextAndPosition(text: CharSequence?) {
    text ?: return
    setText(text)
    setSelection(text.length)
}

fun setupActionBar(actionBar: ActionBar?) {
    actionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setDisplayShowHomeEnabled(true)
    }
}

fun Toast.small(): Toast {
    return this.also {
        val toastLayout = it.view as ViewGroup
        val toastTextView = toastLayout.getChildAt(0) as TextView
        toastTextView.textSize = 12f
    }
}