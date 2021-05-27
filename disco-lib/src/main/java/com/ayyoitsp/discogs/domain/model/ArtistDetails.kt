/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class ArtistDetails(
    val artistId: String,
    val resourceUrl: String,
    val nameVariations: List<String>,
    val members: List<ArtistMember>
)