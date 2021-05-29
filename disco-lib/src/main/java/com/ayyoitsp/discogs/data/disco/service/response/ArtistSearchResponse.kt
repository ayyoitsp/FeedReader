package com.ayyoitsp.discogs.data.disco.service.response

import com.google.gson.annotations.SerializedName

/**
 * Represents data from the 'results' field in the search response.
 */
data class ArtistSearchResponse (
    val thumb: String,
    val title: String,
    val type: String,
    @SerializedName("cover_image") val coverImageUrl: String,
    val id: Int,
)
