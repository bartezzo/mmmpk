package com.tobzzo.mmmpk.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureDayEnum
import timber.log.Timber


fun View.show(show: Boolean) {
    if (show) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

// Crossinline helps avoiding non-local control flow.

inline fun <T> LiveData<T>.subscribe(lifecycle: LifecycleOwner, crossinline onChanged: (T) -> Unit) {
    observe(lifecycle, Observer { it?.run(onChanged) })
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun String.fromStringNumberToIntNumber(): Int = try {
    this.toInt()
} catch (ex: Exception) {
    Timber.e(ex, "error parsing string as Int")
    0
}

fun String.fromStringDayToEnumDay(): DepartureDayEnum = try {
    DepartureDayEnum.valueOf(this)
} catch (ex: Exception) {
    Timber.e(ex, "error parsing string as DepartureDayEnum")
    DepartureDayEnum.NONE
}

fun Int.fromIntDayToEnumDay(): DepartureDayEnum = DepartureDayEnum.values()[this]

fun View.changeViewVisibility() {
    this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

