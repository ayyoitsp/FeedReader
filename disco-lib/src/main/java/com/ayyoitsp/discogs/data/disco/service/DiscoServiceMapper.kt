/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.ayyoitsp.discogs.domain.model.*

interface DiscoServiceMapper {
    fun mapArtistSearchToDomain(response: PagedSearchResultsResponse): SearchResponse<Artist>

    fun mapReleaseSearchToDomain(response: PagedSearchResultsResponse): SearchResponse<Release>

    fun mapArtistDetailsToDomain(response: ArtistDetailsResponse): ArtistDetails

    fun mapArtistReleasesToDomain(response: List<ReleaseResponse>): List<Release>

    fun mapReleaseDetailsToDomain(response: ReleaseDetailsResponse) : ReleaseDetails
}