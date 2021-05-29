/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.utils

import com.ayyoitsp.discogs.presentation.ErrorType

interface ViewUtils {
    fun mapErrorToStringResource(errorType: ErrorType): Int
}