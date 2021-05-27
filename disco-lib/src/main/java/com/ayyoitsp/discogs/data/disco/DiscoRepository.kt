/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco

import com.ayyoitsp.discogs.domain.model.*

interface DiscoRepository {
    suspend fun getArtistSearchResults(searchRequest: SearchRequest): SearchResponse<Artist>

    suspend fun getArtistDetails(artistId: String): ArtistDetails

    suspend fun getArtistReleases(artistId: String): List<ReleaseModel>

    suspend fun getReleaseDetails(releaseId: String): ReleaseDetails

    suspend fun getReleaseSearchResults(searchRequest: SearchRequest): SearchResponse<ReleaseModel>
}