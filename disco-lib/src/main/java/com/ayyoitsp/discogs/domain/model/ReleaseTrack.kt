/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

/**
 * Domain representation the track in an album
 */
data class ReleaseTrack(
    val position: String,
    val title: String,
    val duration: String,
)