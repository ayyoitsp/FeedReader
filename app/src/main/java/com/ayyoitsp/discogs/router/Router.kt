/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.router

import com.ayyoitsp.discogs.domain.model.Artist

interface Router {

    fun showSearchScreen()

    fun showArtistDetails(artistId: String)

    fun showReleases(artist: Artist)
}