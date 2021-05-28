/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import com.ayyoitsp.discogs.domain.model.Release
import com.ayyoitsp.discogs.presentation.ErrorType

data class ReleaseListViewState(
    val loading: Boolean,
    val releases: List<Release>,
    val errorType: ErrorType?,
)
