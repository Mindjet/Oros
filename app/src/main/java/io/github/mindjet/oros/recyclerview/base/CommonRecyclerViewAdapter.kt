package io.github.mindjet.oros.recyclerview.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class CommonRecyclerViewAdapter<T : ILayoutId> : RecyclerView.Adapter<CommonViewHolder>() {

    var data: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getLayoutId()
    }

}