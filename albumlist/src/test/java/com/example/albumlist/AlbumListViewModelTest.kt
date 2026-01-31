package com.example.albumlist

import app.cash.turbine.test
import com.example.albumlist.model.AlbumListViewModel
import com.example.domain.model.Album
import com.example.domain.repository.AlbumRepository
import com.example.navigation.Navigator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
internal class AlbumListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var navigator: Navigator

    @MockK
    lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun viewModelCallsRepositoryOnCreation() = runTest {
        every { albumRepository.getAlbumList() } returns flowOf(listOf(mockk<Album>(relaxed = true)))

        createSut()

        coEvery { albumRepository.fetchAlbumList() }
    }

    @Test
    fun viewModelGetsAlbumData() = runTest {
        every { albumRepository.getAlbumList() } returns flowOf(listOf(mockk<Album>(relaxed = true) {
            every { title } returns "title"
        }))

        val sut = createSut()

        sut.albums.test {
            awaitItem()
            val result = awaitItem()

            assertEquals(result.size, 1)
            assertEquals(result.first().title, "title")
        }
    }

    private fun createSut() = AlbumListViewModel(navigator, albumRepository)
}