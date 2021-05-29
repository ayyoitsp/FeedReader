/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.data.disco.service

import com.ayyoitsp.discogs.data.disco.service.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit description of the discogs API https://www.discogs.com/developers#page:database
 */
interface DiscoService {
    /**
     * Searches only for artists
     */
    @GET("database/search?type=artist")
    suspend fun searchArtists(
        @Query("q") artist: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int,
    ): PagedSearchResultsResponse<ArtistSearchResponse>

    /**
     * Searches only for releases
     */
    @GET("database/search?type=release")
    suspend fun searchReleases(
        @Query("q") releaseTitle: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int,
    ): PagedSearchResultsResponse<ReleaseSearchResponse>

    /**
     * Gets artist details
     */
    @GET("artists/{artistId}")
    suspend fun getArtistDetails(
        @Path("artistId") artistId: String
    ): ArtistDetailsResponse

    /**
     * Gets releases for an artist
     */
    @GET("artists/{artistId}/releases?sort=year&sort_order=desc")
    suspend fun getArtistReleases(
        @Path("artistId") artistId: String
    ): List<ReleaseResponse>

    /**
     * Get release details
     */
    @GET("database/releases/{releaseId}")
    suspend fun getReleaseDetails(
        @Path("releaseId") releaseId: String
    ): ReleaseDetailsResponse


}