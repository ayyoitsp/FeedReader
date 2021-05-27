/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.ayyoitsp.discogs.domain.model.*

class DiscoServiceMapperImpl : DiscoServiceMapper {
    override fun mapArtistSearchToDomain(response: PagedSearchResultsResponse): SearchResponse<Artist> =
        with(response) {
            SearchResponse(
                this.pagination.page,
                this.pagination.perPage,
                this.pagination.pages,
                mapArtistResults(this.results),
            )
        }

    private fun mapArtistResults(results: List<SearchResultResponse>): List<Artist> =
        results.map {
            with(it) {
                Artist(
                    id.toString(),
                    resourceUrl,
                    thumb,
                    title
                )
            }
        }

    override fun mapReleaseSearchToDomain(response: PagedSearchResultsResponse): SearchResponse<ReleaseModel> =
        with(response) {
            SearchResponse(
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

    override fun mapArtistDetailsToDomain(response: ArtistDetailsResponse): ArtistDetails =
        with(response) {
            ArtistDetails(
                id.toString(),
                resourceUrl,
                namevariations,
                mapMembersToDomain(members)
            )
        }

    private fun mapMembersToDomain(response: List<MemberResponse>): List<ArtistMember> =
        response.map {
            with(it) {
                ArtistMember(
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

    override fun mapReleaseDetailsToDomain(response: ReleaseDetailsResponse): ReleaseDetails =
        with(response) {
            ReleaseDetails(
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

    private fun mapTrackList(response: List<ReleaseTracks>): List<ReleaseTrack> =
        response.map {
            with(it) {
                ReleaseTrack(position, title, duration)
            }
        }
}