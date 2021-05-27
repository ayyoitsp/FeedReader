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

    override suspend fun getArtistDetails(artistId: String): ArtistDetails {
        val response = discoService.getArtistDetails(artistId)

        return discoServiceMapper.mapArtistDetailsToDomain(response)
    }

    override suspend fun getArtistReleases(artistId: String): List<ReleaseModel> {
        val response = discoService.getArtistReleases(artistId)

        return discoServiceMapper.mapArtistReleasesToDomain(response)
    }

    override suspend fun getReleaseDetails(releaseId: String): ReleaseDetails {
        val response = discoService.getReleaseDetails(releaseId)

        return discoServiceMapper.mapReleaseDetailsToDomain(response)
    }

    override suspend fun getReleaseSearchResults(searchRequest: SearchRequest): SearchResponse<ReleaseModel> {
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