package com.fakhri0079.cineshelf2.network

import com.fakhri0079.cineshelf2.model.Cinema
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/indraazimi/mobpro1-compose/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface CinemaApiService {
    @GET("static-api.json")
    suspend fun getCinema(): List<Cinema>
}

object CinemaApi{
    val service: CinemaApiService by lazy {
        retrofit.create(CinemaApiService::class.java)
    }
}