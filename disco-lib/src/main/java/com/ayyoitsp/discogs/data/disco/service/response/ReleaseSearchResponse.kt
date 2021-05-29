/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

data class ReleaseSearchResponse(
    val thumb: String,
    val title: String,
    val type: String,
    val id: Int,
    val year: Int,
)
