package io.github.mindjet.oros.ext

import android.content.Intent
import android.support.annotation.StringRes
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.mindjet.oros.BaseApplication
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.base.ActivityHub
import java.util.*

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

fun Any.getLanguageIndex(): Int {
    val language = Locale.getDefault().language
    return if (language == Locale("zh").language) Constant.LANGUAGE_CN else Constant.LANGUAGE_OTHER
}

fun String.withSpace(): String {
    return this + " "
}

fun <T> Any.makeIntent(clazz: Class<T>): Intent {
    return Intent(ActivityHub.getCurrentActivity(), clazz)
}

fun Intent.putString(key: String, value: String): Intent {
    this.putExtra(key, value)
    return this
}

fun Any.startActivity(intent: Intent) {
    ActivityHub
            .getCurrentActivity()
            .startActivity(intent)
}