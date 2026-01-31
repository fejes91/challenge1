package com.example.data.di

import com.example.data.network.AppleRSSApi
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                    println("Requesting URL: ${request.url}")
                    val response = chain.proceed(request)
                    println("Received response for URL: ${response.request.url} with status code: ${response.code}")
                    response
                }
                .build()
        }

        @Provides
        fun provideObjectMapper(): ObjectMapper {
            return ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
            )
        }

        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            objectMapper: ObjectMapper,
            @BaseUrl baseUrl: String
        ): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build()
        }

        @Provides
        internal fun provideAppleRSSApi(retrofit: Retrofit): AppleRSSApi {
            return retrofit.create(AppleRSSApi::class.java)
        }
}
