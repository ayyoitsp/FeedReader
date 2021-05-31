/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data

import com.ayyoitsp.discogs.MockData.Companion.ARTIST_1
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_2
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_DETAILS_MEMBERS
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_DETAILS_NO_MEMBERS
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_DETAILS_RESPONSE_MEMBERS
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_DETAILS_RESPONSE_NO_MEMBERS
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_RELEASE_1
import com.ayyoitsp.discogs.MockData.Companion.ARTIST_RELEASE_2
import com.ayyoitsp.discogs.MockData.Companion.PAGED_ARTIST_SEARCH_RESPONSE_1
import com.ayyoitsp.discogs.MockData.Companion.PAGED_RELEASES_RESPONSES_1
import com.ayyoitsp.discogs.MockData.Companion.PAGINATION_1
import com.ayyoitsp.discogs.MockData.Companion.RELEASE_DETAILS_1
import com.ayyoitsp.discogs.MockData.Companion.RELEASE_DETAILS_RESPONSE_1
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapperImpl
import org.junit.Test

class DiscoServiceMapperTest {

    private val mapper = DiscoServiceMapperImpl()

    @Test
    fun `Validate artist search mapping`() {

        val response = PAGED_ARTIST_SEARCH_RESPONSE_1
        val mapped = mapper.mapArtistSearchToDomain(response)

        assert(mapped.pageNumber == PAGINATION_1.page)
        assert(mapped.pageSize == PAGINATION_1.perPage)
        assert(mapped.totalPages == PAGINATION_1.pages)
        assert(mapped.results.size == 2)

        assert(mapped.results[0] == ARTIST_1)
        assert(mapped.results[1] == ARTIST_2)
    }

    @Test
    fun `Validate artist details mapping with no members`() {

        val response = ARTIST_DETAILS_RESPONSE_NO_MEMBERS
        val mapped = mapper.mapArtistDetailsToDomain(response)

        assert(mapped == ARTIST_DETAILS_NO_MEMBERS)
    }

    @Test
    fun `Validate artist details mapping with members`() {

        val response = ARTIST_DETAILS_RESPONSE_MEMBERS
        val mapped = mapper.mapArtistDetailsToDomain(response)

        assert(mapped == ARTIST_DETAILS_MEMBERS)
    }

    @Test
    fun `Validate artist releases mapping`() {

        val response = PAGED_RELEASES_RESPONSES_1
        val mapped = mapper.mapReleaseSearchToDomain(response)

        assert(mapped.pageNumber == PAGINATION_1.page)
        assert(mapped.pageSize == PAGINATION_1.perPage)
        assert(mapped.totalPages == PAGINATION_1.pages)
        assert(mapped.results.size == 2)

        assert(mapped.results[0] == ARTIST_RELEASE_1)
        assert(mapped.results[1] == ARTIST_RELEASE_2)
    }

    @Test
    fun `Validate release details mapping`() {

        val response = RELEASE_DETAILS_RESPONSE_1
        val mapped = mapper.mapReleaseDetailsToDomain(response)

        assert(mapped == RELEASE_DETAILS_1)
    }

}