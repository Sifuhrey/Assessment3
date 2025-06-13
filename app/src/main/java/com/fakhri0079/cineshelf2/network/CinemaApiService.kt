package com.fakhri0079.cineshelf2.network

import com.fakhri0079.cineshelf2.model.CinemaResponse
import com.fakhri0079.cineshelf2.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "http://202.74.74.239:3000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CinemaApiService {

    @GET("/cinemas")
    suspend fun getCinema(
    @Query("userId") userId: String
    ): CinemaResponse

    @Multipart
    @POST("/cinemas")
    suspend fun postCinema(
        @Part("userId") userId: String,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("rating") rating: RequestBody,
        @Part("isWatched") isWatched: RequestBody,
        @Part imageUrl: MultipartBody.Part
    ) : OpStatus

    @DELETE("/cinemas/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ) : OpStatus
}

object CinemaApi{
    val service: CinemaApiService by lazy {
        retrofit.create(CinemaApiService::class.java)
    }

    fun getCinema(imageId: String): String{
        return imageId
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED, EMPTY}