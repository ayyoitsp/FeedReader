/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

import com.ayyoitsp.discogs.data.disco.service.Pagination

data class PagedArtistSearchResponse(
    val pagination: Pagination,
    val results: List<ArtistSearchResponse>
)
