/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.utils

import com.ayyoitsp.discogs.presentation.ErrorType

/**
 * Helpful mapping utility for view data.
 *
 * Keeps view/resource information out of ViewModels
 */
interface ViewUtils {
    fun mapErrorToStringResource(errorType: ErrorType): Int
}