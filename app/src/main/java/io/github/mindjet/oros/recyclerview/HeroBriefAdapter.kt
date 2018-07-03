package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.model.HeroBrief
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroBriefAdapter : CommonRecyclerViewAdapter<HeroBrief>() {

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.itemView.apply {
            text_view_hero_name.text = data[position].name
        }
    }

}