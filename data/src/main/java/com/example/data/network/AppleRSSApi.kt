package com.example.data.network

import com.example.data.network.apimodel.AppleRSSResponse
import retrofit2.http.GET


internal interface AppleRSSApi {
    @GET("de/music/most-played/100/albums.json")
    suspend fun fetchTop100GermanAlbums(): AppleRSSResponse
}