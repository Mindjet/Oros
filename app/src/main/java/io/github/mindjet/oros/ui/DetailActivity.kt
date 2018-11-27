package io.github.mindjet.oros.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.base.BaseActivity
import io.github.mindjet.oros.ext.log
import io.github.mindjet.oros.ext.withSpace
import io.github.mindjet.oros.model.Hero
import io.github.mindjet.oros.network.ApiManager
import io.github.mindjet.oros.network.NetworkHandler
import io.github.mindjet.oros.network.OwService
import io.github.mindjet.oros.recyclerview.HeroDetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailActivity : BaseActivity() {

    private lateinit var id: String
    private lateinit var name: String
    private lateinit var adapter: HeroDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initProps()
        iniView()
        getHeroDetail()
    }

    private fun initProps() {
        id = intent.getStringExtra(Constant.EXTRA_KEY_ID)
        name = intent.getStringExtra(Constant.EXTRA_KEY_NAME)
    }

    private fun iniView() {
        tv_hero_name.text = name
        iv_arrow_back.setOnClickListener { finish() }
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = HeroDetailAdapter()
        recycler_view.adapter = adapter
    }

    private fun getHeroDetail() {
        ApiManager.getService<OwService>()
                .getHeroDetail(id)
                .doOnSubscribe { pb_loading.visibility = View.VISIBLE }
                .subscribeOn(Schedulers.io())
                .map { it[0] }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { pb_loading.visibility = View.GONE }
                .doOnCompleted { pb_loading.visibility = View.GONE }
                .subscribe(this::render, NetworkHandler::onError)
    }

    private fun render(hero: Hero) {
        adapter.data.add(hero)
        adapter.notifyDataSetChanged()
    }

}