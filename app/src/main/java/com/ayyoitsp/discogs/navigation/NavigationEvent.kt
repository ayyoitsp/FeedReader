/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.navigation

import com.ayyoitsp.discogs.domain.model.Artist

/**
 * Describes the navigation events that can occur in this app.
 *
 * Abstracts implementation away from events so our ViewModels can trigger
 * navigation events without knowing about implementation details.
 */
sealed class NavigationEvent {
    class ArtistReleases(val artist: Artist) : NavigationEvent()

    class ArtistDetails(val artistId: String) : NavigationEvent()

    class ReleaseDetails(val releaseId: String) : NavigationEvent()
}