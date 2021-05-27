/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

data class PagedSearchResultsResponse(
    val pagination: Pagination,
    val results: List<SearchResultResponse>
)
