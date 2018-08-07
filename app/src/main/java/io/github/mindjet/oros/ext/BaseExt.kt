package io.github.mindjet.oros.ext

import android.app.Activity
import android.util.Log

fun Activity.log(content: Any?) {
    Log.i(this::class.simpleName, content.toString())
}