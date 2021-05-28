/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artist

import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.presentation.ErrorType

data class ArtistDetailsViewState(
    val loading: Boolean,
    val artistDetails: ArtistDetails?,
    val errorType: ErrorType?,
)

