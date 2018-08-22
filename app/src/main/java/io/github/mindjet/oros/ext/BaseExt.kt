package io.github.mindjet.oros.ext

import android.support.annotation.StringRes
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.mindjet.oros.BaseApplication

fun Any.log(content: Any?) {
    Log.i(this::class.simpleName, content.toString())
}

fun Any.toast(content: Any?) {
    Toast.makeText(BaseApplication.getContext(), content.toString(), Toast.LENGTH_SHORT).show()
}

fun Any.toast(@StringRes stringId: Int) {
    toast(BaseApplication.getContext().resources.getString(stringId))
}

fun ImageView.loadRounded(url: String) {
    Glide.with(this)
            .load(url)
            .thumbnail(0.2f)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}

fun ImageView.load(url: String) {
    Glide.with(this)
            .load(url)
            .thumbnail(.2f)
            .into(this)
}