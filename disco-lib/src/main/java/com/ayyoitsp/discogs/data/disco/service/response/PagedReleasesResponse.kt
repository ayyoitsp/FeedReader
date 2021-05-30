/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

/**
 * Paged release response from discogs service
 */
data class PagedReleasesResponse(
    val pagination: Pagination,
    val releases: List<ReleaseResponse>
)

