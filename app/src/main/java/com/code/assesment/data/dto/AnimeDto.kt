package com.code.assesment.data.dto

import com.code.assesment.domain.model.Anime
import com.code.assesment.domain.model.Result

data class AnimeDto(
    val last_page: Int,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val results: List<Result>
) {
    data class Result(
        val airing: Boolean,
        val end_date: String,
        val episodes: Int,
        val image_url: String,
        val mal_id: Int,
        val members: Int,
        val rated: String,
        val score: Double,
        val start_date: String,
        val synopsis: String,
        val title: String,
        val type: String,
        val url: String
    )
}

fun AnimeDto.toAnime() = Anime(lastPage = this.last_page, results = this.results.map {
    Result(it.title, it.type, it.url, it.members, it.rated, it.score, it.image_url)
})
