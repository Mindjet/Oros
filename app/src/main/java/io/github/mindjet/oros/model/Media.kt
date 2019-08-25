package io.github.mindjet.oros.model

import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId
import java.io.Serializable

data class Media(
        val name: String,
        val thumbnail: String,
        val type: String,
        val source: String
) : ILayoutId, Serializable {
    override fun getLayoutId(): Int {
        return R.layout.item_media
    }
}