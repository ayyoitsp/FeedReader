/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class SearchRequest(
    val queryString: String,
    val pageNumber: Int,
    val pageSize: Int,
)