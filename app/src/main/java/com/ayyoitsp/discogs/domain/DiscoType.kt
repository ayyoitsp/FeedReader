package com.ayyoitsp.discogs.domain

sealed class DiscoType(type: String) {
    object Artist : DiscoType("artist")
    object Release : DiscoType("release")
}
