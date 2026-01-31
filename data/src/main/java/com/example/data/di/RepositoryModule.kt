package com.example.data.di

import com.example.data.repository.DefaultAlbumRepository
import com.example.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    internal abstract fun bindAlbumRepository(
        defaultAlbumRepository: DefaultAlbumRepository
    ): AlbumRepository
}