package io.github.mindjet.oros.model


//"total": 24,
//"first": "http:\/\/overwatch-api.net\/api\/v1\/hero?page=1",
//"next": null,
//"previous": null,
//"last": "http:\/\/overwatch-api.net\/api\/v1\/hero?page=1",
//"data": []


data class Wrapper<T>(
        val total: String,
        val first: String,
        val next: String,
        val previous: String,
        val last: String,
        val data: List<T>
)