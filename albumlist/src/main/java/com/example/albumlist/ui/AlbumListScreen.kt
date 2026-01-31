package com.example.albumlist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.albumlist.model.AlbumListViewModel
import com.example.domain.model.Album

@Composable
fun AlbumListScreen(viewModel: AlbumListViewModel = hiltViewModel()) {
    val albums by viewModel.albumList.collectAsStateWithLifecycle()

    AlbumListScreenContent(albums)
}

@Composable
private fun AlbumListScreenContent(albums: List<Album>) {
    LazyColumn {
        items(albums.size) { index ->
            Text(albums[index].title)
        }
    }
}

@Composable
@Preview
private fun AlbumListScreenPreview() {

    AlbumListScreenContent(emptyList())
}