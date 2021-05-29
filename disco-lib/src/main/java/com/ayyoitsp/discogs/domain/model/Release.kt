/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.domain.model

data class Release(
    val releaseId: String,
    val thumbUrl: String,
    val title: String,
    val year: String,
)