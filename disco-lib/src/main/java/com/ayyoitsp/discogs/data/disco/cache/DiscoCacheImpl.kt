/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.model.*

/**
 * Simple in-memory implementation of the discogs cache.
 *
 * Not very memory efficient, could be replaced with LRUs or otherwise based on expected user
 * behavior.
 */
class DiscoCacheImpl : DiscoCache {

    private val searchResultsMap = mutableMapOf<SearchRequest, SearchResponse<Artist>>()
    private val artistDetailsMap = mutableMapOf<String, ArtistDetails>()
    private val artistReleasesMap = mutableMapOf<String, List<Release>>()
    private val releaseDetailsMap = mutableMapOf<String, ReleaseDetails>()

    @Synchronized
    override fun getSearchResults(searchRequest: SearchRequest): SearchResponse<Artist>? =
        searchResultsMap[searchRequest]

    @Synchronized
    override fun cacheSearchResults(
        searchRequest: SearchRequest,
        searchResults: SearchResponse<Artist>
    ) {
        searchResultsMap[searchRequest] = searchResults
    }

    @Synchronized()
    override fun getArtistDetails(artistId: String): ArtistDetails? = artistDetailsMap[artistId]

    @Synchronized()
    override fun cacheArtistDetails(artistDetails: ArtistDetails) {
        artistDetailsMap[artistDetails.artistId] = artistDetails
    }

    @Synchronized()
    override fun getArtistReleases(artistId: String): List<Release>? =
        artistReleasesMap[artistId]

    @Synchronized()
    override fun cacheArtistReleases(artistId: String, releases: List<Release>) {
        artistReleasesMap[artistId] = releases
    }

    @Synchronized()
    override fun getReleaseDetails(releaseId: String): ReleaseDetails? =
        releaseDetailsMap[releaseId]

    @Synchronized()
    override fun cacheReleaseDetails(releaseDetails: ReleaseDetails) {
        releaseDetailsMap[releaseDetails.releaseId] = releaseDetails
    }
}