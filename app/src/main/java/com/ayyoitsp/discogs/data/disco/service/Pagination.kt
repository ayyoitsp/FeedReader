/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.google.gson.annotations.SerializedName

data class Pagination(
    val page: Int,
    val pages: Int,
    val items: Int,
    @SerializedName("per_page") val perPage: Int,
    val urls: Map<String, String>
)