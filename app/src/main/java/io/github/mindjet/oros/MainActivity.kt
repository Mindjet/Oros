package io.github.mindjet.oros

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.mindjet.oros.recyclerview.CommonRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CommonRecyclerViewAdapter<String>()
        adapter.data = morkData()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    private fun morkData(): MutableList<String> {
        val list = mutableListOf<String>()
        var i = 0
        while (i < 10) {
            i++
            list.add("")
        }
        return list
    }
}
