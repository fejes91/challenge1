package com.example.albumlist.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Album
import com.example.domain.repository.AlbumRepository
import com.example.navigation.Navigator
import com.example.navigation.route.AlbumDetailsScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val albumRepository: AlbumRepository
) : ViewModel() {
    private val uiStateFlow = MutableStateFlow<UiState>(Idle)
    val uiState = uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                uiStateFlow.value = Loading
                uiStateFlow.value = Results(albumRepository.fetchAlbumList())
            } catch (e: Exception) {
                uiStateFlow.value = Error("Something went wrong")
            }
        }
    }

    fun onSave(albumId: String) {
        viewModelScope.launch {
            albumRepository.saveAlbum(albumId)
        }
    }

    fun onSelect(albumId: String) {
        navigator.navigateTo(AlbumDetailsScreenRoute(albumId))
    }
}

sealed interface UiState

object Idle : UiState
object Loading : UiState
data class Results(val albums: List<Album>) : UiState
data class Error(val message: String) : UiState
