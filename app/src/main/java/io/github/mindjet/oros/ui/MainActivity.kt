package io.github.mindjet.oros.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.mindjet.oros.R
import io.github.mindjet.oros.base.BaseActivity
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.network.ApiManager
import io.github.mindjet.oros.network.NetworkHandler
import io.github.mindjet.oros.network.OwService
import io.github.mindjet.oros.recyclerview.HeroBriefAdapter
import io.github.mindjet.oros.recyclerview.decoration.MarginDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_network_error.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : BaseActivity() {

    private lateinit var adapter: HeroBriefAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = HeroBriefAdapter()
        recycler_view.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
//        recycler_view.addItemDecoration(MarginDecoration(resources.getDimensionPixelSize(R.dimen.card_margin)))
        recycler_view.adapter = adapter

        btn_reload.setOnClickListener { getHeroList() }

        getHeroList()
    }

    private fun getHeroList() {
        ApiManager.getService<OwService>()
                .getHeroList()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { content_network_error.visibility = View.GONE; pb_loading.visibility = View.VISIBLE }
                .flatMap { Observable.from(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { pb_loading.visibility = View.GONE }
                .doOnCompleted { pb_loading.visibility = View.GONE }
                .subscribe(this::handleHeroList, this::handleError)
    }

    private fun handleHeroList(heroBrief: HeroBrief) {
        adapter.data.add(heroBrief)
        adapter.notifyItemInserted(adapter.data.size - 1)
    }

    private fun handleError(t: Throwable) {
        NetworkHandler.onError(t)
        content_network_error.visibility = View.VISIBLE
    }

}
