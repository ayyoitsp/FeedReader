/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco

import com.ayyoitsp.discogs.domain.model.*

/**
 * Main repository for fetching discography data
 */
interface DiscoRepository {
    suspend fun getArtistSearchResults(searchRequest: SearchRequest): SearchResponse<Artist>

    suspend fun getArtistDetails(artistId: String): ArtistDetails

    suspend fun getArtistReleases(artistId: String): List<Release>

    suspend fun getReleaseDetails(releaseId: String): ReleaseDetails

    suspend fun getReleaseSearchResults(searchRequest: SearchRequest): SearchResponse<Release>
}