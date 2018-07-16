package io.github.mindjet.oros.network

import android.util.Log
import io.github.mindjet.oros.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {

    private const val TAG = "ApiManager"
    private const val DEFAULT_TIMEOUT = 5000L

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor {
                val request = it.request()
                Log.i(TAG, "---> ${request.method()}" + " ${request.url()}")
                it.proceed(request)
            }
            .build()

    inline fun <reified T> getService(): T {
        return retrofit.create(T::class.java)
    }

}