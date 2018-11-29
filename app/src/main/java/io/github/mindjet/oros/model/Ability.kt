package io.github.mindjet.oros.model

import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId

data class Ability(
        val name: String,
        val description: String,
        val icon: String,
        val video: String
): ILayoutId {
    override fun getLayoutId(): Int {
        return R.layout.item_ability
    }

}