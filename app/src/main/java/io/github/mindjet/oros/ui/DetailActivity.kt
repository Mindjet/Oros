package io.github.mindjet.oros.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.base.BaseActivity
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.log
import io.github.mindjet.oros.ext.withSpace
import io.github.mindjet.oros.model.Hero
import io.github.mindjet.oros.model.OwHero
import io.github.mindjet.oros.network.ApiManager
import io.github.mindjet.oros.network.NetworkHandler
import io.github.mindjet.oros.network.OwService
import io.github.mindjet.oros.recyclerview.HeroAbilityAdapter
import io.github.mindjet.oros.recyclerview.HeroDetailAdapter
import io.github.mindjet.oros.recyclerview.HeroMediaAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.tv_hero_name
import kotlinx.android.synthetic.main.content_ability.view.*
import kotlinx.android.synthetic.main.content_basic_info.view.*
import kotlinx.android.synthetic.main.content_brief.view.*
import kotlinx.android.synthetic.main.content_media.view.*
import kotlinx.android.synthetic.main.content_slogan.view.*
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailActivity : BaseActivity() {

    private lateinit var adapter: HeroDetailAdapter

    private lateinit var hero: OwHero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        hero = intent.extras?.getSerializable(Constant.OW_HERO_SERIALIZABLE) as OwHero

        iniView()
        render(hero)
    }

    private fun iniView() {
        tv_hero_name.text = hero.name
        iv_arrow_back.setOnClickListener { finish() }
//        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        adapter = HeroDetailAdapter()
//        recycler_view.adapter = adapter
        content_hero.apply {
            iv_hero_avatar.load(hero.largeAvatar)
            tv_hero_name.text = hero.name
            tv_hero_position.text = hero.position
            tv_hero_affiliation.text = hero.affiliation
            tv_hero_base.text = hero.baseOfOperation
            tv_hero_bio.text = hero.bio
            tv_hero_brief.text = hero.description

            val abilityAdapter = HeroAbilityAdapter()
            recycler_view_ability.adapter = abilityAdapter
            recycler_view_ability.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            abilityAdapter.data.addAll(hero.abilities)
            abilityAdapter.notifyDataSetChanged()

            val mediaAdapter = HeroMediaAdapter()
            recycler_view_media.adapter = mediaAdapter
            recycler_view_media.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mediaAdapter.data.addAll(hero.media)
            mediaAdapter.notifyDataSetChanged()
        }
    }


    private fun render(hero: OwHero) {
//        adapter.data.add(hero)
//        adapter.notifyDataSetChanged()
    }

}