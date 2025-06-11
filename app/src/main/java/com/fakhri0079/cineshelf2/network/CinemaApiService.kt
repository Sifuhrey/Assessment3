package com.fakhri0079.cineshelf2.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/indraazimi/mobpro1-compose/static-api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface CinemaApiService {
    @GET("static-api.json")
    suspend fun getCinema(): String
}

object CinemaApi{
    val service: CinemaApiService by lazy {
        retrofit.create(CinemaApiService::class.java)
    }
}