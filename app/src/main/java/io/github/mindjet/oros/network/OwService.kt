package io.github.mindjet.oros.network

import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.model.HeroBrief
import io.github.mindjet.oros.model.Wrapper
import retrofit2.http.GET
import rx.Observable

interface OwService {

    @GET(Constant.HERO_BRIEF_URL)
    fun getHeroList(): Observable<List<HeroBrief>>

}