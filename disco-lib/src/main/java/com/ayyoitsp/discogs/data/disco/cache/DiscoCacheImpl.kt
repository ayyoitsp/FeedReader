/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.domain.model.ReleaseModel

class DiscoCacheImpl : DiscoCache {

    private val artistDetailsMap = mutableMapOf<String, ArtistDetails>()
    private val artistReleasesMap = mutableMapOf<String, List<ReleaseModel>>()
    private val releaseDetailsMap = mutableMapOf<String, ReleaseDetails>()

    @Synchronized
    override fun getArtistDetails(artistId: String): ArtistDetails? {
        TODO("Not yet implemented")
    }

    override fun cacheArtistDetails(artistDetails: ArtistDetails) {
        TODO("Not yet implemented")
    }

    override fun getArtistReleases(artistId: String): List<ReleaseModel>? {
        TODO("Not yet implemented")
    }

    override fun cacheArtistRelease(artistId: String, releases: List<ReleaseModel>) {
        TODO("Not yet implemented")
    }

    override fun getReleaseDetails(releaseId: String): ReleaseDetails? {
        TODO("Not yet implemented")
    }

    override fun cacheReleaseDetails(releaseDetails: ReleaseDetails) {
        TODO("Not yet implemented")
    }
}