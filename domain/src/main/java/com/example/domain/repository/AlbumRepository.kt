package com.example.domain.repository

import com.example.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun fetchAlbumList()

    suspend fun saveAlbum(id: String)

    fun getAlbumList(): Flow<List<Album>>
}