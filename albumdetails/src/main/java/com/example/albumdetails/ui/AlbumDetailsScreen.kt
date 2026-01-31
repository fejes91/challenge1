package com.example.albumdetails.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.albumdetails.model.AlbumDetailsViewModel

@Composable
fun AlbumDetailsScreen(albumId: String, viewModel: AlbumDetailsViewModel = hiltViewModel()) {
    AlbumDetailsScreenContent(albumId)
}

@Composable
private fun AlbumDetailsScreenContent(albumId: String) {
    Text("AlbumDetailsScreen: $albumId")
}

@Composable
@Preview
private fun AlbumDetailsScreenPreview() {
    AlbumDetailsScreenContent("123")
}