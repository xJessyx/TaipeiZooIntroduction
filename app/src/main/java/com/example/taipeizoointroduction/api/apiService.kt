package com.example.taipeizoointroduction.api

import com.example.taipeizoointroduction.data.AnimalData
import com.example.taipeizoointroduction.data.ZooData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val webapiDomain: String = "https://data.taipei/api/v1/dataset/"

private val client = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .writeTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .build()

private val baseRetrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(webapiDomain)
    .client(client)
    .build()

interface ApiService {

    //館區簡介
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    suspend fun getAreaOverview(
        @Query("scope") scope: String = "resourceAquire"
    ): ZooData

    //動物資料
    @GET("a3e2b221-75e0-45c1-8f97-75acbd43d613")
    suspend fun getAnimals(
        @Query("scope") scope: String = "resourceAquire"
    ): AnimalData

}

object ZooApi {
    val retrofitApiService: ApiService by lazy { baseRetrofit.create(ApiService::class.java) }
}