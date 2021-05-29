/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.navigation

import androidx.fragment.app.FragmentManager
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.presentation.artist.ArtistDetailsFragment
import com.ayyoitsp.discogs.presentation.artistrelease.ReleaseListFragment
import com.ayyoitsp.discogs.presentation.search.SearchFragment

class RouterImpl(private val supportFragmentManager: FragmentManager) : Router {
    override fun showSearchScreen() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, SearchFragment.newInstance())
            .commit()
    }

    override fun showArtistDetails(artistId: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, ArtistDetailsFragment.newInstance(artistId))
            .commit()
    }

    override fun showReleases(artist: Artist) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, ReleaseListFragment.newInstance(artist))
            .commit()
    }
}