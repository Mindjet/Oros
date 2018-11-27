package io.github.mindjet.oros.model

import com.google.gson.annotations.SerializedName
import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId


data class Hero(
        val id: String,
        val name: String,
        val description: String,
        val story: String,
        val bio: String,
        val position: String,
        @SerializedName("base_of_operation") val base: String,
        val affiliation: String,
        val avatar: String,
        @SerializedName("large_avatar") val largeAvatar: String,
        @SerializedName("ability_set") val abilitySet: List<Ability>,
        @SerializedName("media_set") val mediaSet: List<Media>
) : ILayoutId {
    override fun toString(): String {
        return "Hero: name=$name, position=$position, bio=$bio"
    }

    override fun getLayoutId(): Int {
        return R.layout.content_hero
    }
}