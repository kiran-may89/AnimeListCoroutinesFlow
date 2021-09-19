package com.code.assesment.data.repository

import com.code.assesment.data.dto.AnimeDto
import com.code.assesment.data.remote.JikanApi
import com.code.assesment.domain.repository.DataRespository
import retrofit2.Response

class DataRepositoryImpl(val api: JikanApi):DataRespository {
    override suspend fun getAnime(query: String): Response<AnimeDto> {
       return api.getAnimes(query)
    }
}