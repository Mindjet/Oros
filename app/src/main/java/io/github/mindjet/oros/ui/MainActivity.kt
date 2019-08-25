package io.github.mindjet.oros.ui

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.mindjet.oros.R
import io.github.mindjet.oros.base.BaseActivity
import io.github.mindjet.oros.model.OwHero
import io.github.mindjet.oros.network.ApiManager
import io.github.mindjet.oros.network.NetworkHandler
import io.github.mindjet.oros.network.OwService
import io.github.mindjet.oros.recyclerview.HeroBriefAdapter
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
        recycler_view.adapter = adapter

        btn_reload.setOnClickListener { getHeroList() }

        getHeroList()
    }

    private fun getHeroList() {
        ApiManager.getService<OwService>()
                .getOwHeroes()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { content_network_error.visibility = View.GONE; pb_loading.visibility = View.VISIBLE }
                .flatMap { Observable.from(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { pb_loading.visibility = View.GONE }
                .doOnCompleted { pb_loading.visibility = View.GONE }
                .subscribe(this::handleHeroList, this::handleError)
    }

    private fun handleHeroList(hero: OwHero) {
        adapter.data.add(hero)
        adapter.notifyItemInserted(adapter.data.size - 1)
    }

    private fun handleError(t: Throwable) {
        NetworkHandler.onError(t)
        content_network_error.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this).create()
        dialog.apply {
            setMessage(context.resources.getString(R.string.sure_to_exit))
            setButton(DialogInterface.BUTTON_POSITIVE, context.resources.getString(R.string.yes)) { _, _ -> super.onBackPressed() }
            setButton(DialogInterface.BUTTON_NEGATIVE, context.resources.getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
            show()
        }
    }

}
