/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco

import com.ayyoitsp.discogs.data.disco.cache.DiscoCache
import com.ayyoitsp.discogs.data.disco.service.DiscoService
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapper
import com.ayyoitsp.discogs.domain.model.*

class DiscoRepositoryImpl(
    private val discoService: DiscoService,
    private val discoServiceMapper: DiscoServiceMapper,
    private val discoCache: DiscoCache,
) : DiscoRepository {
    override suspend fun getArtistSearchResults(searchRequest: SearchRequest): SearchResponse<Artist> {
        val response = with(searchRequest) {
            discoService.searchArtists(
                queryString,
                pageNumber,
                pageSize
            )
        }
        return discoServiceMapper.mapArtistSearchToDomain(response)
    }

    override suspend fun getArtistDetails(artistId: String): ArtistDetails =
        discoCache.getArtistDetails(artistId) ?: fetchAndCacheArtistDetails(artistId)

    private suspend fun fetchAndCacheArtistDetails(artistId: String): ArtistDetails {
        val artistDetails =
            discoServiceMapper.mapArtistDetailsToDomain(discoService.getArtistDetails(artistId))

        discoCache.cacheArtistDetails(artistDetails)

        return artistDetails
    }

    override suspend fun getArtistReleases(artistId: String): List<Release> =
        discoCache.getArtistReleases(artistId) ?: fetchAndCacheArtistReleases(artistId)

    private suspend fun fetchAndCacheArtistReleases(artistId: String): List<Release> {
        val artistReleases =
            discoServiceMapper.mapArtistReleasesToDomain(discoService.getArtistReleases(artistId))

        discoCache.cacheArtistReleases(artistId, artistReleases)

        return artistReleases
    }

    override suspend fun getReleaseDetails(releaseId: String): ReleaseDetails =
        discoCache.getReleaseDetails(releaseId) ?: fetchAndCacheReleaseDetails(releaseId)

    private suspend fun fetchAndCacheReleaseDetails(releaseId: String): ReleaseDetails {
        val releaseDetails =
            discoServiceMapper.mapReleaseDetailsToDomain(discoService.getReleaseDetails(releaseId))

        discoCache.cacheReleaseDetails(releaseDetails)

        return releaseDetails
    }

    override suspend fun getReleaseSearchResults(searchRequest: SearchRequest): SearchResponse<Release> {
        val response = with(searchRequest) {
            discoService.searchReleases(
                queryString,
                pageNumber,
                pageSize
            )
        }
        return discoServiceMapper.mapReleaseSearchToDomain(response)
    }
}