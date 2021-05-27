/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco

import com.ayyoitsp.discogs.domain.*

interface DiscoRepository {
    suspend fun getArtistSearchResults(searchRequestModel: SearchRequestModel): SearchResponseModel<ArtistModel>

    suspend fun getArtistDetails(artistId: String): ArtistDetailsModel

    suspend fun getArtistReleases(artistId: String): List<ReleaseModel>

    suspend fun getReleaseDetails(releaseId: String): ReleaseDetailsModel

    suspend fun getReleaseSearchResults(searchRequestModel: SearchRequestModel): SearchResponseModel<ReleaseModel>
}