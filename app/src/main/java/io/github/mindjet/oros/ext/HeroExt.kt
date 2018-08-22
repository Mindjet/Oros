package io.github.mindjet.oros.ext

import io.github.mindjet.oros.BaseApplication
import io.github.mindjet.oros.R

fun String.getHeroRole(): String {
    val context = BaseApplication.getContext()
    return when (this) {
        "0" -> context.resources.getString(R.string.role_tank)
        "1" -> context.resources.getString(R.string.role_damage)
        "2" -> context.resources.getString(R.string.role_support)
        else -> ""
    }
}