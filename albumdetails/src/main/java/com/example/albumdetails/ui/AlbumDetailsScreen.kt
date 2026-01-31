package com.example.albumdetails.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.albumdetails.model.AlbumDetailsViewModel
import com.example.domain.model.Album

@Composable
fun AlbumDetailsScreen(albumId: String, viewModel: AlbumDetailsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.initialize(albumId)
    }

    val album by viewModel.album.collectAsStateWithLifecycle()

    AlbumDetailsScreenContent(album)
}

@Composable
private fun AlbumDetailsScreenContent(album: Album?) {
    album ?: return
    Text("AlbumDetailsScreen: ${album.title}")
}

@Composable
@Preview
private fun AlbumDetailsScreenPreview() {
    AlbumDetailsScreenContent(null)
}