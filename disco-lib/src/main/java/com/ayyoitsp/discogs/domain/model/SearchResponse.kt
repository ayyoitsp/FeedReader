/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

/**
 * Domain representation a paged search response
 */
data class SearchResponse<T>(
    val pageNumber: Int,
    val pageSize: Int,
    val totalPages: Int,
    val results: List<T>,
)