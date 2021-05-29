/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

import com.google.gson.annotations.SerializedName

data class ReleaseSearchResponse(
    val thumb: String,
    val title: String,
    val type: String,
    @SerializedName("resource_url") val resourceUrl: String,
    val id: Int,
    val year: String,
)
