/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.domain.model.Release
import com.ayyoitsp.discogs.presentation.ErrorType

/**
 * ViewState for a list of [Release]s
 */
data class ReleaseListViewState(
    val loading: Boolean,
    val artist: Artist,
    val releases: List<Release>,
    val errorType: ErrorType? = null,
)
