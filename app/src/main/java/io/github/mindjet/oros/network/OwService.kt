package io.github.mindjet.oros.network

import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.model.Wrapper
import retrofit2.http.GET

interface OwService {

    @GET("/hero")
    fun getHeroList(): rx.Observable<Wrapper<HeroBrief>>

}