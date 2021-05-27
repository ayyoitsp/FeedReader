package com.ayyoitsp.discogs.data.disco.service

import com.google.gson.annotations.SerializedName

data class ReleaseResponse(
    val artist: String,
    val id: Int,
    @SerializedName("resource_url") val resourceUrl: String,
    val thumb: String,
    val title: String,
    val year: String
)