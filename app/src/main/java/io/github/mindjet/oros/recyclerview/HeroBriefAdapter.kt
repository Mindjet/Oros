package io.github.mindjet.oros.recyclerview

import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import kotlinx.android.synthetic.main.item_hero.view.*
import java.util.*

class HeroBriefAdapter : CommonRecyclerViewAdapter<HeroBrief>() {

    private val language: String = Locale.getDefault().language
    private val index = if (language == Locale("zh").language) Constant.LANGUAGE_CN else Constant.LANGUAGE_OTHER

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.itemView.apply {
            text_view_hero_name.text = data[position].name[index]
            val roleResId = when (data[position].role) {
                "0" -> R.drawable.ic_tank
                "1" -> R.drawable.ic_offense
                "2" -> R.drawable.ic_support
                else -> R.drawable.ic_support
            }
            iv_hero_role.setImageResource(roleResId)
        }
    }

}