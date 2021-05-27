/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.ArtistModel
import com.ayyoitsp.discogs.domain.SearchRequestModel

class DiscoCacheImpl : DiscoCache {
    override fun getArtistSearchResults(searchRequestModel: SearchRequestModel): List<ArtistModel>? {
        TODO("Not yet implemented")
    }

    override fun saveArtistSearchResults(searchRequestModel: SearchRequestModel) {
        TODO("Not yet implemented")
    }
}