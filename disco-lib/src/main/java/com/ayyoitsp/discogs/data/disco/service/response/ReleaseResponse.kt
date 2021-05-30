package com.ayyoitsp.discogs.data.disco.service.response

/**
 * Paged release response from discogs service
 */
data class ReleaseResponse(
    val artist: String,
    val id: Int,
    val thumb: String,
    val title: String,
    val year: Int
)