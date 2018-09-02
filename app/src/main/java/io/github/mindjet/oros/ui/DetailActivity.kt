package io.github.mindjet.oros.ui

import android.os.Bundle
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.base.BaseActivity
import io.github.mindjet.oros.ext.withSpace
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        id = intent.getStringExtra(Constant.EXTRA_KEY_ID)
        val name = intent.getStringExtra(Constant.EXTRA_KEY_NAME)
        tv_hero_name.text = name.withSpace()
        iv_arrow_back.setOnClickListener { finish() }
    }

}