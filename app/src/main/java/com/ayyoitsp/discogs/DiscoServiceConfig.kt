/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs

/**
 * Contains configuration parameters for the discogs service.
 *
 * These should live in XML, but for simplicity is hard-coded here
 */
data class DiscoServiceConfig(
    val discogsBaseUrl: String = "https://api.discogs.com/",
    val discogsConsumerKey: String = "ETulIAiLynZRyGbbgJMe",
    val discogsConsumerSecret: String = "odsqGImiZsXwzZZsoTqDUMfSAVYPmRxB",
)