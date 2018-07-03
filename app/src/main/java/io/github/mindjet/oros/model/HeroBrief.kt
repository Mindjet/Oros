package io.github.mindjet.oros.model

import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.ILayoutId

data class HeroBrief(val name: String, val type: String) : ILayoutId {

    override fun getLayoutId(): Int {
        return R.layout.item_hero
    }

}