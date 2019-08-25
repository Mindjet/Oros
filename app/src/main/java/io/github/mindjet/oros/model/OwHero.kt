package io.github.mindjet.oros.model

import com.google.gson.annotations.SerializedName
import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId
import java.io.Serializable

data class OwHero(
        val name: String,
        val description: String,
        val story: String,
        val bio: String,
        val position: String,
        val affiliation: String,
        val abilities: List<Ability>,
        val media: List<Media>,
        @SerializedName("base_of_operation") val baseOfOperation: String,
        @SerializedName("large_avatar") val largeAvatar: String
) : ILayoutId, Serializable {
    override fun getLayoutId(): Int = R.layout.item_hero
}

