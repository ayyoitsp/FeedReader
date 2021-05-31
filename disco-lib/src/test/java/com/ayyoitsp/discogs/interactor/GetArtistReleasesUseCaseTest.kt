package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetArtistReleasesUseCaseTest {

    private val discoRepository: DiscoRepository = mock()

    private val getArtistDetailsUseCase = GetArtistReleasesUseCaseImpl(discoRepository)

    @Test
    fun `Ensure artist details`() {
        runBlockingTest {
            getArtistDetailsUseCase.execute("artistId")
                .collect {  }

            verify(discoRepository).getArtistReleases("artistId")
        }
    }
}