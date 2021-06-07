/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.domain.interactor.GetArtistDetailsUseCaseImpl
import com.ayyoitsp.discogs.domain.repository.DiscoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetArtistDetailsUseCaseTest {

    private val discoRepository: DiscoRepository = mock()

    private val getArtistDetailsUseCase = GetArtistDetailsUseCaseImpl(discoRepository)

    @Test
    fun `Ensure artist details`() {
        runBlockingTest {
            getArtistDetailsUseCase.execute("artistId")
                .collect {  }

            verify(discoRepository).getArtistDetails("artistId")
        }
    }
}