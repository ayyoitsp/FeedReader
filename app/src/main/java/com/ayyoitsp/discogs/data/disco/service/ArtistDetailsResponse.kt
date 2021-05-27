/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.google.gson.annotations.SerializedName

data class ArtistDetailsResponse (
    val id: Int,
    val profile: String,
    @SerializedName("resource_url") val resourceUrl: String,
    val members: List<MemberResponse>,
    val namevariations: List<String>
)

data class MemberResponse(
    val id: Int,
    val active: Boolean,
    val name: String,
    @SerializedName("resource_url") val resourceUrl: String
)