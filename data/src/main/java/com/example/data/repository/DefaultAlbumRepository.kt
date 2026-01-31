package com.example.data.repository

import com.example.data.network.AppleRSSApi
import com.example.data.network.apimodel.toDomainModel
import com.example.domain.model.Album
import com.example.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultAlbumRepository @Inject constructor(
    private val appleRSSApi: AppleRSSApi
) : AlbumRepository {
    private val albumList = MutableStateFlow<List<Album>>(emptyList())

    override suspend fun fetchAlbumList() = withContext(Dispatchers.IO) {
        albumList.value = appleRSSApi.fetchTop100GermanAlbums()
            .feed.results.map { it.toDomainModel() }
    }

    override suspend fun saveAlbum(id: String) = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }

    override fun getAlbumList(): Flow<List<Album>> = albumList

    override fun getSavedAlbums(): Flow<List<Album>> = flowOf(emptyList())
}