/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain

data class ArtistMemberModel(
    val memberId: String,
    val active: Boolean,
    val displayName: String,
    val resourceUrl: String,
)