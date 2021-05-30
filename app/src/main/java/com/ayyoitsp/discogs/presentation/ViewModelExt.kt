/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.ViewModel

/**
 * Maps an error fetching data from the server to an app specific error type
 */
fun ViewModel.mapFetchError(ex: Exception): ErrorType {
    // TODO: map real errors, for now, default to network error
    return ErrorType.Network
}