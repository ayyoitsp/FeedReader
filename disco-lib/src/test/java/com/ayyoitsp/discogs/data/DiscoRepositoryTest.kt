/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data

import com.ayyoitsp.discogs.MockData.Companion.ARTIST_DETAILS_RESPONSE_NO_MEMBERS
import com.ayyoitsp.discogs.MockData.Companion.PAGED_ARTIST_SEARCH_RESPONSE_1
import com.ayyoitsp.discogs.MockData.Companion.PAGED_RELEASES_RESPONSES_1
import com.ayyoitsp.discogs.MockData.Companion.RELEASE_DETAILS_RESPONSE_1
import com.ayyoitsp.discogs.data.disco.DiscoRepositoryImpl
import com.ayyoitsp.discogs.data.disco.cache.DiscoCache
import com.ayyoitsp.discogs.data.disco.service.DiscoService
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapper
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_DETAILS_MEMBERS
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_RELEASES
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_RELEASES_SEARCH_RESPONSE
import com.ayyoitsp.discogs.MockData.Companion.RELEASE_DETAILS_1
import com.ayyoitsp.discogs.MockData.Companion.SEARCH_REQUEST_1
import com.ayyoitsp.discogs.MockData.Companion.SEARCH_RESPONSE_1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class DiscoRepositoryTest {
    private val discoService = mock<DiscoService>()
    private val discoServiceMapper = mock<DiscoServiceMapper>()
    private val discoCache = mock<DiscoCache>()

    private val discoRepository = DiscoRepositoryImpl(discoService, discoServiceMapper, discoCache)

    @Test
    fun `Ensure getArtistSearchResults fetches data from cache when present`() {
        val searchRequest = SEARCH_REQUEST_1
        val searchResponse = SEARCH_RESPONSE_1
        whenever(discoCache.getSearchResults(searchRequest)).thenReturn(searchResponse)

        runBlockingTest {
            val result = discoRepository.getArtistSearchResults(searchRequest)
            assert(searchResponse == result)

            verify(discoCache).getSearchResults(searchRequest)
            verify(discoService, never()).getArtistDetails(any())
        }
    }

    @Test
    fun `Ensure getArtistSearchResults fetches data from server when data not in cache`() {
        val searchRequest = SEARCH_REQUEST_1
        val searchResponse = SEARCH_RESPONSE_1
        whenever(discoCache.getSearchResults(searchRequest)).thenReturn(null)

        runBlockingTest {
            whenever(discoService.searchArtists(any(), any(), any())).thenReturn(
                PAGED_ARTIST_SEARCH_RESPONSE_1
            )
            whenever(discoServiceMapper.mapArtistSearchToDomain(any())).thenReturn(searchResponse)

            val result = discoRepository.getArtistSearchResults(searchRequest)
            assert(searchResponse == result)
            verify(discoService).searchArtists(
                searchRequest.queryString,
                searchRequest.pageNumber,
                searchRequest.pageSize
            )
            verify(discoCache).getSearchResults(searchRequest)

            // ensure data is saved to cache
            verify(discoCache).cacheSearchResults(searchRequest, searchResponse)
        }
    }


    @Test
    fun `Ensure getArtistDetails fetches data from cache when present`() {
        val artistDetails = ARTIST_DETAILS_MEMBERS
        whenever(discoCache.getArtistDetails(artistDetails.artistId)).thenReturn(artistDetails)

        runBlockingTest {
            val result = discoRepository.getArtistDetails(artistDetails.artistId)
            assert(artistDetails == result)

            verify(discoCache).getArtistDetails(artistDetails.artistId)
            verify(discoService, never()).getArtistDetails(any())
        }
    }

    @Test
    fun `Ensure getArtistDetails fetches data from server when data not in cache`() {
        val artistDetails = ARTIST_DETAILS_MEMBERS
        whenever(discoCache.getArtistDetails(artistDetails.artistId)).thenReturn(null)

        runBlockingTest {
            whenever(discoService.getArtistDetails(artistDetails.artistId)).thenReturn(
                ARTIST_DETAILS_RESPONSE_NO_MEMBERS
            )
            whenever(discoServiceMapper.mapArtistDetailsToDomain(any())).thenReturn(artistDetails)

            val result = discoRepository.getArtistDetails(artistDetails.artistId)
            assert(artistDetails == result)
            verify(discoService).getArtistDetails(artistDetails.artistId)
            verify(discoCache).getArtistDetails(artistDetails.artistId)

            // ensure data is saved to cache
            verify(discoCache).cacheArtistDetails(result)
        }
    }

    @Test
    fun `Ensure getArtistReleases fetches data from cache when present`() {
        val artistDetails = ARTIST_DETAILS_MEMBERS
        val artistReleases = ARTIST_RELEASES
        whenever(discoCache.getArtistReleases(artistDetails.artistId)).thenReturn(artistReleases)

        runBlockingTest {
            val result = discoRepository.getArtistReleases(artistDetails.artistId)
            assert(artistReleases == result)

            verify(discoCache).getArtistReleases(artistDetails.artistId)
            verify(discoService, never()).getArtistDetails(any())
        }
    }

    @Test
    fun `Ensure getArtistReleases fetches data from server when data not in cache`() {
        val artistDetails = ARTIST_DETAILS_MEMBERS
        val artistReleases = ARTIST_RELEASES
        whenever(discoCache.getArtistReleases(artistDetails.artistId)).thenReturn(null)

        runBlockingTest {
            whenever(discoService.getArtistReleases(artistDetails.artistId)).thenReturn(
                PAGED_RELEASES_RESPONSES_1
            )
            whenever(discoServiceMapper.mapReleaseSearchToDomain(any())).thenReturn(
                ARTIST_RELEASES_SEARCH_RESPONSE
            )

            val result = discoRepository.getArtistReleases(artistDetails.artistId)
            assert(artistReleases == result)
            verify(discoService).getArtistReleases(artistDetails.artistId)
            verify(discoCache).getArtistReleases(artistDetails.artistId)

            // ensure data is saved to cache
            verify(discoCache).cacheArtistReleases(artistDetails.artistId, result)
        }
    }

    @Test
    fun `Ensure getReleaseDetails fetches data from cache when present`() {
        val releaseDetails = RELEASE_DETAILS_1
        whenever(discoCache.getReleaseDetails(releaseDetails.releaseId)).thenReturn(releaseDetails)

        runBlockingTest {
            val result = discoRepository.getReleaseDetails(releaseDetails.releaseId)
            assert(releaseDetails == result)

            verify(discoCache).getReleaseDetails(releaseDetails.releaseId)
            verify(discoService, never()).getReleaseDetails(any())
        }
    }

    @Test
    fun `Ensure getReleaseDetails fetches data from server when data not in cache`() {
        val releaseDetails = RELEASE_DETAILS_1
        whenever(discoCache.getReleaseDetails(releaseDetails.releaseId)).thenReturn(null)

        runBlockingTest {
            whenever(discoService.getReleaseDetails(releaseDetails.releaseId)).thenReturn(
                RELEASE_DETAILS_RESPONSE_1
            )
            whenever(discoServiceMapper.mapReleaseDetailsToDomain(any())).thenReturn(releaseDetails)

            val result = discoRepository.getReleaseDetails(releaseDetails.releaseId)
            assert(releaseDetails == result)
            verify(discoService).getReleaseDetails(releaseDetails.releaseId)
            verify(discoCache).getReleaseDetails(releaseDetails.releaseId)

            // ensure data is saved to cache
            verify(discoCache).cacheReleaseDetails(result)
        }
    }

}