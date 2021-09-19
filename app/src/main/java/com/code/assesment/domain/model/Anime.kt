package com.code.assesment.domain.model

data class Anime(val lastPage: Int, val results: List<Result>)
data class Result(  val title: String?,
                    val type: String?,
                    val url: String?,
                    val members: Int?,
                    val rated: String?,
                    val score: Double?,
                    val imageUrl: String?)