/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain

import com.ayyoitsp.discogs.domain.model.*

class MockDomain {
    companion object {

        val IMG_URL = "https://image.com/image.jpg"

        val ARTIST_1 = Artist("1", IMG_URL, IMG_URL, "Name")
        val ARTIST_MEMBER_1 = ArtistMember("21", true, "name", IMG_URL)
        val ARTIST_MEMBER_2 = ArtistMember("22", false, "name", IMG_URL)
        val ARTIST_DETAILS_NO_MEMBERS = ArtistDetails("1", "profile", IMG_URL, "name", emptyList())
        val ARTIST_DETAILS_MEMBERS = ArtistDetails(
            "2", "profile", IMG_URL, "name", listOf(
                ARTIST_MEMBER_1, ARTIST_MEMBER_2
            )
        )

        val SEARCH_REQUEST_1 = SearchRequest("", 1, 100)
        val SEARCH_RESPONSE_1 = SearchResponse(1, 1, 1, listOf(ARTIST_1))

        val ARTIST_RELEASE_1 = Release("31", IMG_URL, "title", "1999")
        val ARTIST_RELEASE_2 = Release("32", IMG_URL, "title", "2000")

        val ARTIST_RELEASES = listOf(ARTIST_RELEASE_1, ARTIST_RELEASE_2)
        val ARTIST_RELEASES_SEARCH_RESPONSE = SearchResponse(1, 1, 1,  ARTIST_RELEASES)

        val RELEASE_TRACK_1 = ReleaseTrack("41", "title", "1:00")
        val RELEASE_TRACK_2 = ReleaseTrack("42", "title", "2:00")

        val RELEASE_DETAILS_1 = ReleaseDetails(
            "31",
            "title",
            "notes",
            listOf(RELEASE_TRACK_1, RELEASE_TRACK_2),
            IMG_URL,
            "1999",
            listOf("a1", "a2")
        )

    }
}

