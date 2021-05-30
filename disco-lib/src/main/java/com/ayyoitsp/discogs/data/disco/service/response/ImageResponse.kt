/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service.response

/**
 * Generic image response type from discogs service
 */
data class ImageResponse(
    val type: String,
    val uri: String,
    val uri150: String,
)