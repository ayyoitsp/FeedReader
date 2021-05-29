/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.ViewModel

fun ViewModel.mapError(ex: Exception): ErrorType {
    // TODO: map real errors, for now, list network error
    return ErrorType.Network
}