/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.domain.model.Release

interface DiscoCache {
    fun getArtistDetails(artistId: String): ArtistDetails?

    fun cacheArtistDetails(artistDetails: ArtistDetails)

    fun getArtistReleases(artistId: String): List<Release>?

    fun cacheArtistReleases(artistId: String, releases: List<Release>)

    fun getReleaseDetails(releaseId: String): ReleaseDetails?

    fun cacheReleaseDetails(releaseDetails: ReleaseDetails)
}