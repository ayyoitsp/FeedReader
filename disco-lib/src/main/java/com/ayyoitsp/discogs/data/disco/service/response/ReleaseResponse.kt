package com.ayyoitsp.discogs.data.disco.service.response

/**
 * Paged release response from discogs service
 */
data class ReleaseResponse(
    val id: Int,
    val artist: String,
    val thumb: String,
    val title: String,
    val year: Int
)