/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.navigation

import com.ayyoitsp.discogs.domain.model.Artist

sealed class NavigationEvent {
    class ArtistReleases(val artist: Artist) : NavigationEvent()

    class ArtistDetails(val artistId: String) : NavigationEvent()
}