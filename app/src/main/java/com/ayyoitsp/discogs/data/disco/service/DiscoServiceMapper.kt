/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.ayyoitsp.discogs.domain.*

interface DiscoServiceMapper {
    fun mapArtistSearchToDomain(response: PagedSearchResultsResponse): SearchResponseModel<ArtistModel>

    fun mapReleaseSearchToDomain(response: PagedSearchResultsResponse): SearchResponseModel<ReleaseModel>

    fun mapArtistDetailsToDomain(response: ArtistDetailsResponse): ArtistDetailsModel

    fun mapArtistReleasesToDomain(response: List<ReleaseResponse>): List<ReleaseModel>

    fun mapReleaseDetailsToDomain(response: ReleaseDetailsResponse) : ReleaseDetailsModel
}