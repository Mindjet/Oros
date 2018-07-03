package io.github.mindjet.oros

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.recyclerview.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.HeroBriefAdapter
import io.github.mindjet.oros.recyclerview.ILayoutId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = HeroBriefAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        adapter.data.add(HeroBrief())
        adapter.data.add(HeroBrief())
        adapter.data.add(HeroBrief())

        recycler_view.adapter.notifyDataSetChanged()
    }

}
