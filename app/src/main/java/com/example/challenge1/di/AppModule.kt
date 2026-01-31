package com.example.challenge1.di

import com.example.challenge1.BuildConfig
import com.example.data.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @BaseUrl
        fun bindBaseUrl(): String = BuildConfig.BASE_URL
    }
}
