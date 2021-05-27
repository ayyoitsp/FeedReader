/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class Artist(
    val artistId: String,
    val resourceUrl: String,
    val thumbUrl: String,
    val displayName: String,
)