package com.example.domain.model

data class Album(
    val id: String,
    val title: String,
    val artistName: String,
    val genres: List<String>,
    val thumbnailUrl: String,
    val isSaved: Boolean = false
)