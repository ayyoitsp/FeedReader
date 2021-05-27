/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

data class PagedReleasesResponse(
    val pagination: Pagination,
    val releases: List<ReleaseResponse>
)

