package com.example.data.repository

import com.example.data.network.AppleRSSApi
import com.example.data.network.apimodel.toDomainModel
import com.example.domain.model.Album
import com.example.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultAlbumRepository @Inject constructor(
    private val appleRSSApi: AppleRSSApi
) : AlbumRepository {

    override suspend fun fetchAlbumList() = withContext(Dispatchers.IO) {
        appleRSSApi.fetchTop100GermanAlbums()
            .feed.results.map { it.toDomainModel() }
    }

    override suspend fun saveAlbum(id: String) = withContext(Dispatchers.IO) {

    }

    override fun getSavedAlbums(): Flow<List<Album>> = flowOf(emptyList())
}