package com.example.albumdetails.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.AlbumRepository
import com.example.navigation.Navigator
import com.example.navigation.route.AlbumDetailsScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    navigator: Navigator,
    albumRepository: AlbumRepository
) : ViewModel() {

    private val route = navigator.currentRoute() as? AlbumDetailsScreenRoute
    val album = albumRepository.getAlbumById(route?.id ?: "").stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        null
    )

}