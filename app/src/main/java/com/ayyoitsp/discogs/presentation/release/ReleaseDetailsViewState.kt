package com.ayyoitsp.discogs.presentation.release

import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.presentation.ErrorType

/**
 * ViewState for the [ReleaseDetails] view
 */
data class ReleaseDetailsViewState(
    val loading: Boolean,
    val releaseDetails: ReleaseDetails?,
    val errorType: ErrorType? = null,
)
