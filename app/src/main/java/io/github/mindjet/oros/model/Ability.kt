package io.github.mindjet.oros.model

import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId
import java.io.Serializable

data class Ability(
        val name: String,
        val description: String,
        val icon: String,
        val video: String
): ILayoutId, Serializable {
    override fun getLayoutId(): Int {
        return R.layout.item_ability
    }

}