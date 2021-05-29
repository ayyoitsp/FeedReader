/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.search

import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.presentation.ErrorType

data class SearchViewState(
    val loading: Boolean,
    val searchResults: List<Artist>,
    val showNoResults: Boolean,
    val errorType: ErrorType? = null
)