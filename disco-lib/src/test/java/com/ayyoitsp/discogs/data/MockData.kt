/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data

import com.ayyoitsp.discogs.data.disco.service.response.*
import com.ayyoitsp.discogs.domain.MockDomain

class MockData {
    companion object {
        val IMG_URL = "https://image.com/image.jpg"

        val PAGINATION_1 = Pagination(1, 1, 1, 1, emptyMap())

        val ARTIST_DETAILS_1 =
            ArtistDetailsResponse(1, MockDomain.IMG_URL, emptyList(), emptyList(), "Name")

        val ARTIST_SEARCH_RESPONSE_1 = ArtistSearchResponse(IMG_URL, "title", "type", IMG_URL, 1)
        val PAGED_ARTIST_SEARCH_RESPONSE_1 =
            PagedArtistSearchResponse(PAGINATION_1, listOf(ARTIST_SEARCH_RESPONSE_1))

        val RELEASE_RESPONSE_1 = ReleaseResponse(31, "artist", IMG_URL, "title", 1999)
        val RELEASE_RESPONSE_2 = ReleaseResponse(32, "artist", IMG_URL, "title", 2000)

        val PAGED_RELEASES_RESPONSES_1 = PagedReleasesResponse(PAGINATION_1, listOf(RELEASE_RESPONSE_1, RELEASE_RESPONSE_2))

        val RELEASE_TRACK_RESPONSE_1 = ReleaseTracks("41", "title", "1:00", "track")
        val RELEASE_TRACK_RESPONSE_2 = ReleaseTracks("42", "title", "2:00", "track")

        val RELEASE_DETAILS_RESPONSE_1 = ReleaseDetailsResponse(
            31,
            "title",
            "notes",
            listOf(RELEASE_TRACK_RESPONSE_1, RELEASE_TRACK_RESPONSE_2),
            emptyList(),
            "1999",
            listOf(ReleaseArtist("a1"), ReleaseArtist("a2"))
        )

    }
}