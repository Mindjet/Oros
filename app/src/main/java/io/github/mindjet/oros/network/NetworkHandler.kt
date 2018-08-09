package io.github.mindjet.oros.network

import io.github.mindjet.oros.R
import io.github.mindjet.oros.ext.toast
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkHandler {
    fun onError(t: Throwable?) {
        when (t) {
            is UnknownHostException -> toast(R.string.unknown_hostname)
            is SocketTimeoutException -> toast(R.string.socket_timeout)
            else -> toast(t?.message)
        }
    }
}