package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.makeIntent
import io.github.mindjet.oros.ext.putString
import io.github.mindjet.oros.ext.startActivity
import io.github.mindjet.oros.model.Ability
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import io.github.mindjet.oros.ui.VideoActivity
import kotlinx.android.synthetic.main.item_ability.view.*

class HeroAbilityAdapter : CommonRecyclerViewAdapter<Ability>() {

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val ability = data[position]
        holder.itemView.apply {
            iv_ability_icon.load(ability.icon)
            tv_ability_name.text = if (position == 0) resources.getString(R.string.intro) else ability.name
            setOnClickListener {
                val i = makeIntent(VideoActivity::class.java)
                        .putString(Constant.VIDEO_URL, ability.video)
                        .putString(Constant.VIDEO_TITLE, ability.name)
                startActivity(i)
            }
        }
    }

}