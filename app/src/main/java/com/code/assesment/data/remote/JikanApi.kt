package com.code.assesment.data.remote

import com.code.assesment.data.dto.AnimeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanApi {
    @GET("v3/search/anime")
    suspend fun getAnimes(@Query("q")  query: String? = "naruto"): Response<AnimeDto>
}