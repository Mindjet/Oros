package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.model.OwHero
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroBriefAdapter : CommonRecyclerViewAdapter<OwHero>() {

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val itemData = data[position]
        holder.itemView.apply {
            iv_hero_avatar.load(itemData.largeAvatar)
            tv_hero_name.text = itemData.name
            constraint_layout.setOnClickListener { onItemClick(itemData) }
        }
    }

    private fun onItemClick(itemData: OwHero) {
//        val intent = makeIntent(DetailActivity::class.java)
//                .putString(Constant.EXTRA_KEY_ID, itemData.id)
//                .putString(Constant.EXTRA_KEY_NAME, itemData.name)
//        startActivity(intent)
    }

}