package com.example.data

import app.cash.turbine.test
import com.example.data.network.AppleRSSApi
import com.example.data.network.apimodel.AppleRSSResponse
import com.example.data.network.apimodel.FeedResponse
import com.example.data.network.apimodel.FeedResultResponse
import com.example.data.repository.DefaultAlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
internal class AlbumRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @MockK
    lateinit var appleRSSApi: AppleRSSApi

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun repositoryCallsApi() = runTest {
        val sut = createSut()
        coEvery { appleRSSApi.fetchTop100GermanAlbums() } returns mockk<AppleRSSResponse> {
            every { feed } returns
                    mockk<FeedResponse> {
                        every { results } returns emptyList()
                    }
        }

        sut.fetchAlbumList()

        coVerify { appleRSSApi.fetchTop100GermanAlbums() }
    }

    @Test
    fun repositoryReturnsApiResponse() = runTest {
        val sut = createSut()
        coEvery { appleRSSApi.fetchTop100GermanAlbums() } returns mockk<AppleRSSResponse> {
            every { feed } returns
                    mockk<FeedResponse> {
                        every { results } returns listOf(
                            mockk<FeedResultResponse>(relaxed = true) {
                                every { name } returns "title"
                                every { artistName } returns "artist"
                            }
                        )
                    }
        }

        sut.fetchAlbumList()

        sut.getAlbumList().test {
            val result = awaitItem()
            assertEquals(result.size, 1)
            assertEquals(result.first().artistName, "artist")
            assertEquals(result.first().title, "title")
        }
    }

    private fun createSut() = DefaultAlbumRepository(appleRSSApi)
}