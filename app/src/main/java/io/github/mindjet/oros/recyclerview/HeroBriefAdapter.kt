package io.github.mindjet.oros.recyclerview

import android.view.View
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.toast
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import kotlinx.android.synthetic.main.item_hero.view.*
import java.util.*

class HeroBriefAdapter : CommonRecyclerViewAdapter<HeroBrief>() {

    private val language: String = Locale.getDefault().language
    private val index = if (language == Locale("zh").language) Constant.LANGUAGE_CN else Constant.LANGUAGE_OTHER

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val itemData = data[position]
        holder.itemView.apply {
            iv_hero_avatar.load(itemData.avatar)
            text_view_hero_name.text = itemData.name[index]
            val roleResId = when (itemData.role) {
                "0" -> R.drawable.ic_tank
                "1" -> R.drawable.ic_offense
                "2" -> R.drawable.ic_support
                else -> R.drawable.ic_support
            }
            iv_hero_role.setImageResource(roleResId)
            constraint_layout.setOnClickListener { toast("${itemData.name}") }
            constraint_layout.setOnLongClickListener(this@HeroBriefAdapter::onLongClick)
        }
    }

    private fun onLongClick(view: View): Boolean {

        return true
    }

}