package com.example.albumlist.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.AlbumRepository
import com.example.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val albumRepository: AlbumRepository
) : ViewModel() {

    val albumList = albumRepository.getAlbumList().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            albumRepository.fetchAlbumList()
        }
    }
}