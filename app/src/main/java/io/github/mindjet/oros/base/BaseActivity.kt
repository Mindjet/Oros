package io.github.mindjet.oros.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHub.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityHub.unregister(this)
    }

}