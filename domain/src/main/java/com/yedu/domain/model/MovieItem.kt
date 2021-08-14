package com.yedu.domain.model

import java.io.Serializable


data class MovieItem(
    val id: String,
    val title: String,
    val poster_path: String,
    val overview: String,
    val popularity: String,
    val release_date: String,
    var backdrop_path: String
):Serializable