/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class ReleaseDetails(
    val releaseId: String,
    val title: String,
    val notes: String,
    val trackList: List<ReleaseTrack>,
    val imageUrl: String,
    val year: String,
    val artistNames: List<String>
)