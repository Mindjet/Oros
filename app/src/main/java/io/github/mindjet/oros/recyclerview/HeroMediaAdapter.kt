package io.github.mindjet.oros.recyclerview

import android.view.View
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.model.Media
import io.github.mindjet.oros.recyclerview.base.CommonRecyclerViewAdapter
import io.github.mindjet.oros.recyclerview.base.CommonViewHolder
import kotlinx.android.synthetic.main.item_media.view.*

class HeroMediaAdapter : CommonRecyclerViewAdapter<Media>() {
    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.itemView.apply {
            tv_media_name.text = data[position].name
            iv_icon_play.visibility = if (data[position].type == Constant.TYPE_VIDEO) View.VISIBLE else View.GONE
            iv_media_thumbnail.load(data[position].thumbnail)
            setOnClickListener { }
        }
    }

}