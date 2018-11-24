package io.github.mindjet.oros.model

import com.google.gson.annotations.SerializedName
import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId
import java.io.Serializable

//"id": 0,
//        "name": "D.Va",
//        "position": "重装",
//        "large_avatar": "http://overwatch.nos.netease.com/1/assets/img/pages/heroes/list/dva.png"

data class HeroBrief(
        val id: String,
        val name: String,
//        val description: List<String>,
//        val health: String,
//        val armour: String,
//        val shield: String,
//        @SerializedName("real_name") val realName: List<String>,
//        val age: String,
//        val height: String,
//        val affiliation: List<String>,
//        @SerializedName("base_of_operations") val baseOfOperation: List<String>,
//        val difficulty: String,
//        val url: String,
//        val role: String,
//        val avatar: String,
//        val bio: List<String>)
        @SerializedName("large_avatar") val avatar: String
) : ILayoutId, Serializable {

    private val serialVersionUID: Long = 12345L

    override fun getLayoutId(): Int {
        return R.layout.item_hero
    }

    override fun toString(): String {
        return "HeroBrief[id:$id,name:$name]"
    }

}