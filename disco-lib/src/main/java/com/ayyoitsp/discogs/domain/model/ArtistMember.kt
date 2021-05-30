/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

/**
 * Domain representation the members of a band
 */
data class ArtistMember(
    val memberId: String,
    val active: Boolean,
    val displayName: String,
    val thumbUrl: String,
)