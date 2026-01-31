package com.example.albumdetails.model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    albumRepository: AlbumRepository
) : ViewModel() {
    private val albumId = MutableStateFlow<String?>(null)

    val album = albumId.filterNotNull().flatMapLatest { albumRepository.getAlbumById(it) }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        null
    )

    fun initialize(albumId: String) {
        this.albumId.value = albumId
    }
}