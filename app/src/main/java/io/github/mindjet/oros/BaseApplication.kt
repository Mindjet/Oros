package io.github.mindjet.oros

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApp = this
    }

    companion object {

        var baseApp: BaseApplication? = null

        fun getContext(): Context {
            return baseApp as Context
        }
    }

}