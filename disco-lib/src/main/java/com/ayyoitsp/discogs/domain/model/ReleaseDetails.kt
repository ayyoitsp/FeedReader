/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class ReleaseDetails(
    val albumId: String,
    val title: String,
    val country: String,
    val genres: List<String>,
    val notes: String,
    val trackList: List<ReleaseTrack>,
    val resourceUrl: String,
    val year: String,
)