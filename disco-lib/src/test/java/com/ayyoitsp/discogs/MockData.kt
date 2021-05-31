/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs

import com.ayyoitsp.discogs.data.disco.service.response.*
import com.ayyoitsp.discogs.domain.model.*

class MockData {
    companion object {

        val COVER_IMG_URL = "https://image.com/cover.jpg"
        val THUMB_IMG_URL = "https://image.com/image.jpg"

        val COVER_IMG_RESPONSE = ImageResponse("primary", COVER_IMG_URL, "")
        val THUMB_IMG_RESPONSE = ImageResponse("primary", THUMB_IMG_URL, "")

        val PAGINATION_1 = Pagination(1, 1, 1, 1, emptyMap())

        // Artist search objects

        // Domain
        val ARTIST_1 = Artist("1", COVER_IMG_URL, THUMB_IMG_URL, "artistname1")
        val ARTIST_2 = Artist("2", COVER_IMG_URL, THUMB_IMG_URL, "artistname2")

        // API
        val ARTIST_SEARCH_RESPONSE_1 = with(ARTIST_1) {
            ArtistSearchResponse(
                thumbUrl,
                displayName,
                "",
                coverImageUrl,
                artistId.toInt()
            )
        }
        val ARTIST_SEARCH_RESPONSE_2 = with(ARTIST_2) {
            ArtistSearchResponse(
                thumbUrl,
                displayName,
                "",
                coverImageUrl,
                artistId.toInt()
            )
        }

        val PAGED_ARTIST_SEARCH_RESPONSE_1 =
            PagedArtistSearchResponse(
                PAGINATION_1,
                listOf(ARTIST_SEARCH_RESPONSE_1, ARTIST_SEARCH_RESPONSE_2)
            )


        val SEARCH_REQUEST_1 = SearchRequest("", 1, 100)
        val SEARCH_RESPONSE_1 = SearchResponse(1, 1, 1, listOf(ARTIST_1))


        // Artist details objects

        // Domain
        val ARTIST_MEMBER_1 = ArtistMember("21", true, "membername1", THUMB_IMG_URL)
        val ARTIST_MEMBER_2 = ArtistMember("22", false, "membername2", THUMB_IMG_URL)

        val ARTIST_DETAILS_NO_MEMBERS =
            ArtistDetails("1", "profile", COVER_IMG_URL, "name", emptyList())
        val ARTIST_DETAILS_MEMBERS = ArtistDetails(
            "2", "profile2", COVER_IMG_URL, "name", listOf(
                ARTIST_MEMBER_1, ARTIST_MEMBER_2
            )
        )

        // API
        val ARTIST_MEMBER_RESPONSE_1 = with(ARTIST_MEMBER_1) {
            MemberResponse(
                memberId.toInt(),
                active,
                displayName,
                thumbUrl
            )
        }

        val ARTIST_MEMBER_RESPONSE_2 = with(ARTIST_MEMBER_2) {
            MemberResponse(
                memberId.toInt(),
                active,
                displayName,
                thumbUrl
            )
        }

        val ARTIST_DETAILS_RESPONSE_NO_MEMBERS =
            ArtistDetailsResponse(
                ARTIST_DETAILS_NO_MEMBERS.artistId.toInt(),
                ARTIST_DETAILS_NO_MEMBERS.profile,
                listOf(COVER_IMG_RESPONSE),
                null,
                ARTIST_DETAILS_NO_MEMBERS.displayName
            )
        val ARTIST_DETAILS_RESPONSE_MEMBERS =
            ArtistDetailsResponse(
                ARTIST_DETAILS_MEMBERS.artistId.toInt(),
                ARTIST_DETAILS_MEMBERS.profile,
                listOf(COVER_IMG_RESPONSE),
                listOf(ARTIST_MEMBER_RESPONSE_1, ARTIST_MEMBER_RESPONSE_2),
                ARTIST_DETAILS_MEMBERS.displayName
            )

        // Artist releases objects

        // Domain
        val ARTIST_RELEASE_1 = Release("31", THUMB_IMG_URL, "title1", "1999")
        val ARTIST_RELEASE_2 = Release("32", THUMB_IMG_URL, "title2", "2000")

        val ARTIST_RELEASES = listOf(ARTIST_RELEASE_1, ARTIST_RELEASE_2)

        // API
        val ARTIST_RELEASES_SEARCH_RESPONSE = SearchResponse(1, 1, 1, ARTIST_RELEASES)

        val RELEASE_RESPONSE_1 = with(ARTIST_RELEASE_1) {
            ReleaseResponse(releaseId.toInt(), "", thumbUrl, title, year.toInt())
        }
        val RELEASE_RESPONSE_2 = with(ARTIST_RELEASE_2) {
            ReleaseResponse(releaseId.toInt(), "", thumbUrl, title, year.toInt())
        }

        val PAGED_RELEASES_RESPONSES_1 =
            PagedReleasesResponse(PAGINATION_1, listOf(RELEASE_RESPONSE_1, RELEASE_RESPONSE_2))

        // Release details objects

        // Domain
        val RELEASE_TRACK_1 = ReleaseTrack("41", "track1", "1:00")
        val RELEASE_TRACK_2 = ReleaseTrack("42", "track2", "2:00")

        val RELEASE_DETAILS_1 = ReleaseDetails(
            "31",
            "title",
            "notes",
            listOf(RELEASE_TRACK_1, RELEASE_TRACK_2),
            COVER_IMG_URL,
            "1999",
            listOf("a1", "a2")
        )

        // API
        val RELEASE_TRACK_RESPONSE_1 = with(RELEASE_TRACK_1) {
            ReleaseTracks(position, title, duration, "track")
        }
        val RELEASE_TRACK_RESPONSE_2 = with(RELEASE_TRACK_2) {
            ReleaseTracks(position, title, duration, "track")
        }

        val RELEASE_DETAILS_RESPONSE_1 = with(RELEASE_DETAILS_1) {
            ReleaseDetailsResponse(
                releaseId.toInt(),
                title,
                notes,
                listOf(RELEASE_TRACK_RESPONSE_1, RELEASE_TRACK_RESPONSE_2),
                listOf(COVER_IMG_RESPONSE),
                year,
                artistNames.map { ReleaseArtist(it) }
            )
        }
    }
}