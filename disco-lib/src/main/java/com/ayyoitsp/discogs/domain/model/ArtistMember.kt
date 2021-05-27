/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class ArtistMember(
    val memberId: String,
    val active: Boolean,
    val displayName: String,
    val resourceUrl: String,
)