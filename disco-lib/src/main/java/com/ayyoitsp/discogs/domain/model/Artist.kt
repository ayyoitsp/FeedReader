/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

import java.io.Serializable

data class Artist(
    val artistId: String,
    val coverImageUrl: String,
    val thumbUrl: String,
    val displayName: String,
) : Serializable