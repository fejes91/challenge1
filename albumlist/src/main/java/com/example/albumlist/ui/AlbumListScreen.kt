package com.example.albumlist.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlbumListScreen() {
    AlbumListScreenContent()
}

@Composable
private fun AlbumListScreenContent() {
    Text("Album list")
    println("!!!!! asdasd")
}

@Composable
@Preview
private fun AlbumListScreenPreview() {

    AlbumListScreenContent()
}