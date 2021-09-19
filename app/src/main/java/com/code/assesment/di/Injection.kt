package com.code.assesment.di

import com.code.assesment.BuildConfig
import com.code.assesment.data.remote.JikanApi
import com.code.assesment.data.repository.DataRepositoryImpl
import com.code.assesment.domain.repository.DataRespository
import com.code.assesment.domain.usecase.DataUseCase
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    private fun providesDataRepository(): DataRespository {
        return DataRepositoryImpl(providesJikanApi())
    }
    fun providesDataUseCase():DataUseCase{
        return DataUseCase(providesDataRepository())
    }

    private fun providesJikanApi(): JikanApi {
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val gson = GsonBuilder()
        gson.setPrettyPrinting()
        val client = OkHttpClient.Builder()
        client.addInterceptor(logginInterceptor)
        val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson.create()))
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpClient())
                .build()
                .create(JikanApi::class.java)
        return retrofit
    }

}