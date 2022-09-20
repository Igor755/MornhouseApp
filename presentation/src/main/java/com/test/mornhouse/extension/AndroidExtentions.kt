package com.test.mornhouse.extension

import android.content.Context
import android.os.Parcel
import android.view.View
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.test.mornhouse.listener.SafeClickListener

fun Fragment.getDimens(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun Context.getDimens(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun View.getDimens(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun Parcel.readNullableString() = readString() ?: ""

fun Parcel.createStringArrayListOrEmptyList() = createStringArrayList() ?: emptyList<String>()

fun Parcel.readBoolean() = readByte() != 0.toByte()

fun AppCompatEditText.placeCursorToEnd() {
    this.post {
        this.text?.let { this.setSelection(it.length) }
    }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

