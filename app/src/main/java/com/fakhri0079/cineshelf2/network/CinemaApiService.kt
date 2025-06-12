package com.fakhri0079.cineshelf2.network

import com.fakhri0079.cineshelf2.model.CinemaResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://202.74.74.239:3000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CinemaApiService {

    @GET("/cinemas?userId=example@example.com")
    suspend fun getCinema(): CinemaResponse
}

object CinemaApi{
    val service: CinemaApiService by lazy {
        retrofit.create(CinemaApiService::class.java)
    }

    fun getCinema(imageId: String): String{
        return imageId
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}