/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.ayyoitsp.discogs.domain.*

class DiscoServiceMapperImpl : DiscoServiceMapper {
    override fun mapArtistSearchToDomain(response: PagedSearchResultsResponse): SearchResponseModel<ArtistModel> =
        with(response) {
            SearchResponseModel(
                this.pagination.page,
                this.pagination.perPage,
                this.pagination.pages,
                mapArtistResults(this.results),
            )
        }

    private fun mapArtistResults(results: List<SearchResultResponse>): List<ArtistModel> =
        results.map {
            with(it) {
                ArtistModel(
                    id.toString(),
                    resourceUrl,
                    thumb,
                    title
                )
            }
        }

    override fun mapReleaseSearchToDomain(response: PagedSearchResultsResponse): SearchResponseModel<ReleaseModel> =
        with(response) {
            SearchResponseModel(
                this.pagination.page,
                this.pagination.perPage,
                this.pagination.pages,
                mapReleaseResults(this.results),
            )
        }

    private fun mapReleaseResults(results: List<SearchResultResponse>): List<ReleaseModel> =
        results.map {
            with(it) {
                ReleaseModel(
                    id.toString(),
                    resourceUrl,
                    thumb,
                    title,
                    year ?: ""
                )
            }
        }

    override fun mapArtistDetailsToDomain(response: ArtistDetailsResponse): ArtistDetailsModel =
        with(response) {
            ArtistDetailsModel(
                id.toString(),
                resourceUrl,
                namevariations,
                mapMembersToDomain(members)
            )
        }

    private fun mapMembersToDomain(response: List<MemberResponse>): List<ArtistMemberModel> =
        response.map {
            with(it) {
                ArtistMemberModel(
                    id.toString(),
                    active,
                    name,
                    resourceUrl,
                )
            }
        }

    override fun mapArtistReleasesToDomain(response: List<ReleaseResponse>): List<ReleaseModel> =
        response.map {
            with(it) {
                ReleaseModel(
                    id.toString(),
                    resourceUrl,
                    thumb,
                    title,
                    year
                )
            }
        }

    override fun mapReleaseDetailsToDomain(response: ReleaseDetailsResponse): ReleaseDetailsModel =
        with(response) {
            ReleaseDetailsModel(
                id.toString(),
                title,
                country,
                genres,
                notes,
                mapTrackList(tracklist),
                resourceUrl,
                year
            )
        }

    private fun mapTrackList(response: List<ReleaseTracks>): List<TrackModel> =
        response.map {
            with(it) {
                TrackModel(position, title, duration)
            }
        }
}