package com.ayyoitsp.discogs.data.disco.service.response

import com.google.gson.annotations.SerializedName

data class ReleaseDetailsResponse(
    val id: Int,
    val title: String,
    val country: String,
    val genres: List<String>,
    val notes: String,
    val tracklist: List<ReleaseTracks>,
    @SerializedName("resource_url") val resourceUrl: String,
    val year: String,
)

data class ReleaseTracks(
    val position: String,
    val title: String,
    val duration: String,
)