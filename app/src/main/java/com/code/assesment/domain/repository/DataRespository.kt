package com.code.assesment.domain.repository

import com.code.assesment.data.dto.AnimeDto
import retrofit2.Response

interface DataRespository {
    suspend fun getAnime(query: String):Response<AnimeDto>
}