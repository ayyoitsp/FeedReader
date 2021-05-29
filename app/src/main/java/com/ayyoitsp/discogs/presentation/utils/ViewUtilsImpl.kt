/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.utils

import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.presentation.ErrorType

class ViewUtilsImpl : ViewUtils {
    override fun mapErrorToStringResource(errorType: ErrorType): Int =
        when (errorType) {
            ErrorType.Network -> R.string.error_network
            ErrorType.NotFound -> R.string.error_not_found
            ErrorType.RateLimit -> R.string.error_rate_limit
            ErrorType.Unknown -> R.string.error_unknown
        }
}