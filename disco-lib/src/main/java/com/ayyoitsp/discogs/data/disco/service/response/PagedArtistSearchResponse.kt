/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

/**
 * Paged artist search response from discogs service
 */
data class PagedArtistSearchResponse(
    val pagination: Pagination,
    val results: List<ArtistSearchResponse>
)
