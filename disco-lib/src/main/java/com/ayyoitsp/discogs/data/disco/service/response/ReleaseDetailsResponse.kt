package com.ayyoitsp.discogs.data.disco.service.response

data class ReleaseDetailsResponse(
    val id: Int,
    val title: String,
    val notes: String,
    val tracklist: List<ReleaseTracks>,
    val images: List<ImageResponse>,
    val year: String,
    val artists: List<ReleaseArtist>
)

data class ReleaseArtist(
    val name: String
)

data class ReleaseTracks(
    val position: String,
    val title: String,
    val duration: String,
    val type_: String,
)