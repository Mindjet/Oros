package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.base.ActivityHub
import io.github.mindjet.oros.ext.*
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import io.github.mindjet.oros.ui.DetailActivity
import io.github.mindjet.oros.ui.HeroBriefBottomSheet
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroBriefAdapter : CommonRecyclerViewAdapter<HeroBrief>() {

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val itemData = data[position]
        holder.itemView.apply {
            iv_hero_avatar.load(itemData.avatar)
            tv_hero_name.text = itemData.name
            constraint_layout.setOnClickListener { onItemClick(itemData) }
        }
    }

    private fun onItemClick(itemData: HeroBrief) {
        val intent = makeIntent(DetailActivity::class.java)
                .putString(Constant.EXTRA_KEY_ID, itemData.id)
                .putString(Constant.EXTRA_KEY_NAME, itemData.name)
        startActivity(intent)
    }

}