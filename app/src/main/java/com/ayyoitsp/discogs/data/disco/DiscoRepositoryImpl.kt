/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco

import com.ayyoitsp.discogs.data.disco.cache.DiscoCache
import com.ayyoitsp.discogs.data.disco.service.DiscoService
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapper
import com.ayyoitsp.discogs.domain.*

class DiscoRepositoryImpl(
    private val discoService: DiscoService,
    private val discoServiceMapper: DiscoServiceMapper,
    private val discoCache: DiscoCache,
) : DiscoRepository {
    override suspend fun getArtistSearchResults(searchRequestModel: SearchRequestModel): SearchResponseModel<ArtistModel> {
        val response = with(searchRequestModel) {
            discoService.searchArtists(
                queryString,
                pageNumber,
                pageSize
            )
        }
        return discoServiceMapper.mapArtistSearchToDomain(response)
    }

    override suspend fun getArtistDetails(artistId: String): ArtistDetailsModel {
        val response = discoService.getArtistDetails(artistId)

        return discoServiceMapper.mapArtistDetailsToDomain(response)
    }

    override suspend fun getArtistReleases(artistId: String): List<ReleaseModel> {
        val response = discoService.getArtistReleases(artistId)

        return discoServiceMapper.mapArtistReleasesToDomain(response)
    }

    override suspend fun getReleaseDetails(releaseId: String): ReleaseDetailsModel {
        val response = discoService.getReleaseDetails(releaseId)

        return discoServiceMapper.mapReleaseDetailsToDomain(response)
    }

    override suspend fun getReleaseSearchResults(searchRequestModel: SearchRequestModel): SearchResponseModel<ReleaseModel> {
        val response = with(searchRequestModel) {
            discoService.searchReleases(
                queryString,
                pageNumber,
                pageSize
            )
        }
        return discoServiceMapper.mapReleaseSearchToDomain(response)
    }
}