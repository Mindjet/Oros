package io.github.mindjet.oros.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.mindjet.oros.R

class CommonRecyclerViewAdapter<T> : RecyclerView.Adapter<CommonViewHolder>() {

    lateinit var data: MutableList<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_hero
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {

    }


}