package io.github.mindjet.oros.ext

import android.app.Activity
import android.support.annotation.StringRes
import android.util.Log
import android.widget.Toast
import io.github.mindjet.oros.BaseApplication

fun Activity.log(content: Any?) {
    Log.i(this::class.simpleName, content.toString())
}

fun Any.toast(content: Any?) {
    Toast.makeText(BaseApplication.getContext(), content.toString(), Toast.LENGTH_SHORT).show()
}

fun Any.toast(@StringRes stringId: Int) {
    toast(BaseApplication.getContext().resources.getString(stringId))
}