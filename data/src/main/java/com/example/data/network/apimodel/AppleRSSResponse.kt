package com.example.data.network.apimodel

import com.example.domain.model.Album
import com.fasterxml.jackson.annotation.JsonProperty

internal data class AppleRSSResponse(
    @param:JsonProperty("feed")
    val feed: FeedResponse
)

internal data class FeedResponse(
    @param:JsonProperty("results")
    val results: List<FeedResultResponse>
)

internal data class FeedResultResponse(
    @param:JsonProperty("id")
    val id: String,
    @param:JsonProperty("artistName")
    val artistName: String,
    @param:JsonProperty("name")
    val name: String,
    @param:JsonProperty("genres")
    val genres: List<Genre>,
    @param:JsonProperty("artworkUrl100")
    val artworkUrl100: String
)

internal data class Genre(
    @param:JsonProperty("name")
    val name: String
)

internal fun FeedResultResponse.toDomainModel(): Album = Album(
    id = id,
    title = name,
    artistName = artistName,
    genres = genres.map { it.name },
    thumbnailUrl = artworkUrl100,
)
