package io.github.mindjet.oros.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

class CommonRecyclerViewAdapter<T : ILayoutId> : RecyclerView.Adapter<CommonViewHolder>() {

    lateinit var data: MutableList<T>

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

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.itemView.apply {
            setOnClickListener { Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show() }
        }
    }


}