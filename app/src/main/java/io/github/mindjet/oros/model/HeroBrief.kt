package io.github.mindjet.oros.model

import com.google.gson.annotations.SerializedName
import io.github.mindjet.oros.R
import io.github.mindjet.oros.recyclerview.base.ILayoutId

//{
//    "id": 23,
//    "name": "Sombra",
//    "description": "Stealth and debilitating attacks make Sombra a powerful infiltrator. Her hacking can disrupt her enemies, ensuring they're easier to take out, while her EMP provides the upper hand against multiple foes at once. Sombra\u2019s ability to Translocate and camouflage herself makes her a hard target to pin down.",
//    "health": 200,
//    "armour": 0,
//    "shield": 0,
//    "real_name": "\u2591\u2591\u2591\u2591\u2591\u2591",
//    "age": 30,
//    "height": null,
//    "affiliation": "Talon",
//    "base_of_operations": "Dorado, Mexico",
//    "difficulty": 3,
//    "url": "https:\/\/overwatch-api.net\/api\/v1\/hero\/23"
//}

data class HeroBrief(
        val id: String,
        val name: String,
        val description: String,
        val health: String,
        val armour: String,
        val shield: String,
        @SerializedName("real_name") val realName: String,
        val age: String,
        val height: String,
        val affiliation: String,
        @SerializedName("base_of_operations") val baseOfOperation: String,
        val difficulty: String,
        val url: String)
    : ILayoutId {

    override fun getLayoutId(): Int {
        return R.layout.item_hero
    }

    override fun toString(): String {
        return "HeroBrief[id:$id,name:$name,description:$description]"
    }

}