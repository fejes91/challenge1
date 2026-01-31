package com.example.albumlist.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.albumlist.R
import com.example.albumlist.model.AlbumListViewModel
import com.example.albumlist.model.Error
import com.example.albumlist.model.Idle
import com.example.albumlist.model.Loading
import com.example.albumlist.model.Results
import com.example.albumlist.model.UiState
import com.example.domain.model.Album

@Composable
fun AlbumListScreen(viewModel: AlbumListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AlbumListScreenContent(uiState, viewModel::onSave, viewModel::onSelect)
}

@Composable
private fun AlbumListScreenContent(
    uiState: UiState,
    onSave: (String) -> Unit,
    onSelect: (String) -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is Idle -> {}
            is Loading -> CircularProgressIndicator()
            is Error -> Text(uiState.message, style = MaterialTheme.typography.labelLarge)
            is Results -> AlbumList(uiState.albums, onSave, onSelect)
        }
    }
}

@Composable
private fun AlbumList(albums: List<Album>, onSave: (String) -> Unit, onSelect: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        Text(
            stringResource(R.string.albumlist_title),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(albums.size) { index ->
                AlbumItem(albums[index], index, onSave, onSelect)
            }
        }
    }
}

@Composable
private fun AlbumItem(
    album: Album,
    index: Int,
    onSave: (String) -> Unit,
    onSelect: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(album.id) },
        shape = CardDefaults.elevatedShape
    ) {
        Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            AsyncImage(
                modifier = Modifier
                    .rotate(if (index % 2 == 0) 5f else -5f)
                    .border(BorderStroke(4.dp, Color.White))
                    .size(128.dp),
                model = album.thumbnailUrl, contentDescription = "${album.title} album cover"
            )

            Column {
                Text(album.title)
                Text(album.artistName)
                FlowRow {
                    album.genres.onEach {
                        Text(it)
                    }

                    Button({ onSave(album.id) }) {
                        Text(stringResource(R.string.albumlist_save_button))
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun AlbumListScreenPreview() {

    AlbumListScreenContent(Loading, {}, {})
}