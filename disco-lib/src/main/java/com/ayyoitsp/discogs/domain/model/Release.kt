/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

/**
 * Domain representation the meta data for a release/album
 */
data class Release(
    val releaseId: String,
    val thumbUrl: String,
    val title: String,
    val year: String,
)