/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

import com.google.gson.annotations.SerializedName

data class ArtistDetailsResponse (
    val id: Int,
    val profile: String,
    val images: List<ImageResponse>,
    val members: List<MemberResponse>,
    val name: String,
)

data class MemberResponse(
    val id: Int,
    val active: Boolean,
    val name: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String
)