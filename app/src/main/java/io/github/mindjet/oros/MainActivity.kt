package io.github.mindjet.oros

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.mindjet.oros.ext.log
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.network.ApiManager
import io.github.mindjet.oros.network.OwService
import io.github.mindjet.oros.recyclerview.HeroBriefAdapter
import io.github.mindjet.oros.recyclerview.decoration.VerticalLinearDecoration
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroBriefAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = HeroBriefAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(VerticalLinearDecoration(resources.getDimensionPixelSize(R.dimen.card_margin)))
        recycler_view.adapter = adapter

        ApiManager.getService<OwService>()
                .getHeroList()
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleHeroList, this::handleError)
    }

    private fun handleHeroList(list: List<HeroBrief>) {
        list.forEachIndexed { index, it ->
            adapter.data.add(it)
            adapter.notifyItemInserted(index)
        }
    }

    private fun handleError(throwable: Throwable) {
        log(throwable.message)
    }

}
