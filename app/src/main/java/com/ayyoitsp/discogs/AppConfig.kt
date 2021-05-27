/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs

data class AppConfig(
    val discogsBaseUrl: String = "https://api.discogs.com/",
    val discogsConsumerKey: String = "ETulIAiLynZRyGbbgJMe",
    val discogsConsumerSecret: String = "odsqGImiZsXwzZZsoTqDUMfSAVYPmRxB",
    val requestTokenUrl: String = "https://api.discogs.com/oauth/request_token",
    val authorizeUrl: String = "https://www.discogs.com/oauth/authorize",
    val accessTokenUrl: String = "https://api.discogs.com/oauth/access_token",
    val pageSize: Int = 1,
)