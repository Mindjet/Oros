package io.github.mindjet.oros.model

import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId

data class Media(
        val name: String,
        val thumbnail: String,
        val type: String,
        val source: String
) : ILayoutId {
    override fun getLayoutId(): Int {
        return R.layout.item_media
    }
}