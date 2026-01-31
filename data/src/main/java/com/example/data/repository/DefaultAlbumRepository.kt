package com.example.data.repository

import com.example.data.network.AppleRSSApi
import com.example.data.network.apimodel.toDomainModel
import com.example.domain.model.Album
import com.example.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultAlbumRepository @Inject constructor(
    private val appleRSSApi: AppleRSSApi
) : AlbumRepository {
    private val albumList = MutableStateFlow<List<Album>>(emptyList())

    // TODO use datastore
    private val savedAlbumIds = MutableStateFlow<List<String>>(emptyList())

    override suspend fun fetchAlbumList() = withContext(Dispatchers.IO) {
        albumList.value = appleRSSApi.fetchTop100GermanAlbums()
            .feed.results.map { it.toDomainModel() }
    }

    override suspend fun saveAlbum(id: String) = withContext(Dispatchers.IO) {
        savedAlbumIds.update {
            it.toMutableList() + id
        }
    }

    override fun getAlbumList(): Flow<List<Album>> =
        savedAlbumIds.combine(albumList) { savedIds, all ->
            all.map { it.copy(isSaved = savedIds.contains(it.id)) }
        }

    override fun getAlbumById(id: String): Flow<Album?> =
        albumList.map { albums -> albums.firstOrNull { it.id == id } }

}