/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.domain.model.ReleaseModel

interface DiscoCache {
    fun getArtistDetails(artistId: String): ArtistDetails?

    fun cacheArtistDetails(artistDetails: ArtistDetails)

    fun getArtistReleases(artistId: String): List<ReleaseModel>?

    fun cacheArtistRelease(artistId: String, releases: List<ReleaseModel>)

    fun getReleaseDetails(releaseId: String): ReleaseDetails?

    fun cacheReleaseDetails(releaseDetails: ReleaseDetails)
}