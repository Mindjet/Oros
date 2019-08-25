package io.github.mindjet.oros.recyclerview

import android.support.v7.widget.LinearLayoutManager
import io.github.mindjet.oros.R
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.log
import io.github.mindjet.oros.model.Hero
import io.github.mindjet.oros.model.OwHero
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import kotlinx.android.synthetic.main.content_ability.view.*
import kotlinx.android.synthetic.main.content_basic_info.view.*
import kotlinx.android.synthetic.main.content_brief.view.*
import kotlinx.android.synthetic.main.content_media.view.*
import kotlinx.android.synthetic.main.content_slogan.view.*

class HeroDetailAdapter : CommonRecyclerViewAdapter<OwHero>() {

    private lateinit var abilityAdapter: HeroAbilityAdapter
    private lateinit var mediaAdapter: HeroMediaAdapter

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val hero = data[position]
        holder.itemView.apply {
            iv_hero_avatar.load(hero.largeAvatar)
            tv_hero_name.text = hero.name
            tv_hero_position.text = hero.position
            tv_hero_affiliation.text = hero.affiliation
            tv_hero_base.text = hero.baseOfOperation
            tv_hero_bio.text = hero.bio
            tv_hero_brief.text = hero.description

            abilityAdapter = HeroAbilityAdapter()
            recycler_view_ability.adapter = abilityAdapter
            recycler_view_ability.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            abilityAdapter.data.addAll(hero.abilities)
            abilityAdapter.notifyDataSetChanged()

            mediaAdapter = HeroMediaAdapter()
            recycler_view_media.adapter = mediaAdapter
            recycler_view_media.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mediaAdapter.data.addAll(hero.media)
            mediaAdapter.notifyDataSetChanged()

        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.content_hero
    }

}