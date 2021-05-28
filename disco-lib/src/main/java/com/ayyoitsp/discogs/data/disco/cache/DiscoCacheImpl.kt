/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.domain.model.Release

class DiscoCacheImpl : DiscoCache {

    private val artistDetailsMap = mutableMapOf<String, ArtistDetails>()
    private val artistReleasesMap = mutableMapOf<String, List<Release>>()
    private val releaseDetailsMap = mutableMapOf<String, ReleaseDetails>()

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