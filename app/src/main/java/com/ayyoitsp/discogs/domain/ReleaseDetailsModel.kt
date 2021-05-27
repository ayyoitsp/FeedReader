/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain

data class ReleaseDetailsModel(
    val albumId: String,
    val title: String,
    val country: String,
    val genres: List<String>,
    val notes: String,
    val trackList: List<TrackModel>,
    val resourceUrl: String,
    val year: String,
)