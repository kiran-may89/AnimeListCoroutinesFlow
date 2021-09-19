package com.code.assesment.domain.usecase

import android.util.Log
import com.code.assesment.common.Resources
import com.code.assesment.data.dto.toAnime
import com.code.assesment.domain.model.Anime
import com.code.assesment.domain.repository.DataRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataUseCase(private val repo: DataRespository) {
    suspend fun getAnime(query: String): Flow<Resources<Anime>> = flow {
        emit(Resources.Loading<Anime>())
        try {
            val response = repo.getAnime(query)

            if (response.isSuccessful) {
                val anime = response.body()?.toAnime()
                anime?.let {
                    emit(Resources.Success(it))
                }
            } else {
                emit(Resources.Error<Anime>(response.message()))
            }
        }catch (e:Exception){
            emit(Resources.Error<Anime>(e.message.toString()))
        }


    }.flowOn(Dispatchers.IO)
}