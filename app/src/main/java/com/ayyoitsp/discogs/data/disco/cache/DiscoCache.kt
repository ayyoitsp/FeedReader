/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.cache

import com.ayyoitsp.discogs.domain.ArtistModel
import com.ayyoitsp.discogs.domain.SearchRequestModel

interface DiscoCache {
    fun getArtistSearchResults(searchRequestModel: SearchRequestModel) : List<ArtistModel>?

    fun saveArtistSearchResults(searchRequestModel: SearchRequestModel)
}