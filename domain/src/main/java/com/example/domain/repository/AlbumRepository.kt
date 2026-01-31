package com.example.domain.repository

import com.example.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun fetchAlbumList():List<Album>

    suspend fun saveAlbum(id: String)

    fun getSavedAlbums(): Flow<List<Album>>
}