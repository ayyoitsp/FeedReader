/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.model.SearchRequest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetArtistSearchResultsUseCaseTest {

    private val discoRepository: DiscoRepository = mock()

    private val getArtistDetailsUseCase = GetArtistSearchResultsUseCaseImpl(discoRepository)

    @Test
    fun `Ensure artist details`() {
        val searchRequest = SearchRequest("q", 1, 1)
        runBlockingTest {
            getArtistDetailsUseCase.execute(searchRequest)
                .collect {  }

            verify(discoRepository).getArtistSearchResults(searchRequest)
        }
    }
}