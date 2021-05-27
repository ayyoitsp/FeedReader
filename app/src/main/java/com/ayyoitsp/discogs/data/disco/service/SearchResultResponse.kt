package com.ayyoitsp.discogs.data.disco.service

import com.google.gson.annotations.SerializedName

/**
 * Represents data from the 'results' field in the search response.
 *
 * Cheating a little here - most values are shared, but year only occurs for 'release' type searches
 */
data class SearchResultResponse (
    val thumb: String,
    val title: String,
    val type: String,
    @SerializedName("resource_url") val resourceUrl: String,
    val id: Int,
    val year: String?,
)
