package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.log
import io.github.mindjet.oros.model.Hero
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import kotlinx.android.synthetic.main.content_hero.view.*

class HeroDetailAdapter : CommonRecyclerViewAdapter<Hero>() {
    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val hero = data[position]
        log(hero)
        holder.itemView.apply {
            iv_hero_avatar.load(hero.largeAvatar)
            tv_hero_name.text = hero.name
            tv_hero_position.text = hero.position
            tv_hero_affiliation.text = hero.affiliation
            tv_hero_base.text = hero.base
        }
    }

}