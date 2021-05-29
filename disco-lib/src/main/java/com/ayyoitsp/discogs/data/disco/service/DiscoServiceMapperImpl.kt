/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import android.util.Log
import com.ayyoitsp.discogs.data.disco.service.response.*
import com.ayyoitsp.discogs.domain.model.*

class DiscoServiceMapperImpl : DiscoServiceMapper {
    override fun mapArtistSearchToDomain(response: PagedArtistSearchResponse): SearchResponse<Artist> =
        with(response) {
            SearchResponse(
                this.pagination.page,
                this.pagination.perPage,
                this.pagination.pages,
                mapArtistResults(this.results),
            )
        }

    private fun mapArtistResults(results: List<ArtistSearchResponse>): List<Artist> =
        results.map {
            with(it) {
                Artist(
                    id.toString(),
                    coverImageUrl,
                    thumb,
                    title
                )
            }
        }

    override fun mapReleaseSearchToDomain(response: PagedReleasesResponse): SearchResponse<Release> =
        with(response) {
            SearchResponse(
                pagination.page,
                pagination.perPage,
                pagination.pages,
                mapReleaseResults(releases),
            )
        }

    private fun mapReleaseResults(results: List<ReleaseResponse>): List<Release> =
        results.map {
            with(it) {
                Release(
                    id.toString(),
                    thumb,
                    title,
                    year.toString()
                )
            }
        }

    override fun mapArtistDetailsToDomain(response: ArtistDetailsResponse): ArtistDetails =
        with(response) {
            ArtistDetails(
                id.toString(),
                profile,
                mapPrimaryImageUrl(images),
                name,
                mapMembersToDomain(members)
            )
        }

    private fun mapPrimaryImageUrl(images: List<ImageResponse>): String =
        images.find { it.type == "primary" }?.uri ?: images.first()?.uri ?: ""

    private fun mapMembersToDomain(response: List<MemberResponse>): List<ArtistMember> =
        response.map {
            with(it) {
                ArtistMember(
                    id.toString(),
                    active,
                    name,
                    thumbnailUrl ?: "",
                )
            }
        }.sortedByDescending { it.active }


    override fun mapArtistReleasesToDomain(response: List<ReleaseResponse>): List<Release> =
        response.map {
            with(it) {
                Release(
                    id.toString(),
                    thumb,
                    title,
                    year.toString()
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