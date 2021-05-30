/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

/**
 * Domain representation the detailed data for an artist
 */
data class ArtistDetails(
    val artistId: String,
    val profile: String,
    val imageUrl: String,
    val displayName: String,
    val members: List<ArtistMember>
)