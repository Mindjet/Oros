package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.makeIntent
import io.github.mindjet.oros.ext.startActivity
import io.github.mindjet.oros.model.OwHero
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import io.github.mindjet.oros.ui.DetailActivity
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

    private fun onItemClick(hero: OwHero) {
        val intent = makeIntent(DetailActivity::class.java)
                .putExtra(Constant.OW_HERO_SERIALIZABLE, hero)
        startActivity(intent)
    }

}