/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.di

import com.ayyoitsp.discogs.AppConfig
import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.data.disco.DiscoRepositoryImpl
import com.ayyoitsp.discogs.data.disco.cache.DiscoCache
import com.ayyoitsp.discogs.data.disco.cache.DiscoCacheImpl
import com.ayyoitsp.discogs.data.disco.service.DiscoService
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapper
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapperImpl
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.interactor.*
import com.ayyoitsp.discogs.presentation.artist.ArtistDetailsViewModel
import com.ayyoitsp.discogs.presentation.artistrelease.ReleaseListViewModel
import com.ayyoitsp.discogs.presentation.release.ReleaseDetailsViewModel
import com.ayyoitsp.discogs.presentation.search.SearchViewModel
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import com.ayyoitsp.discogs.presentation.utils.PicassoImageLoaderImpl
import com.ayyoitsp.discogs.presentation.utils.ViewUtils
import com.ayyoitsp.discogs.presentation.utils.ViewUtilsImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DI(private val appConfig: AppConfig) {

    companion object {
        const val COROUTINE_SCOPE_IO = "IO"
        const val COROUTINE_SCOPE_MAIN = "MAIN"
    }

    val scopeModule = module {

        factory(named(COROUTINE_SCOPE_IO)) { (Dispatchers.IO + SupervisorJob()) }

        factory(named(COROUTINE_SCOPE_MAIN)) { CoroutineScope(Dispatchers.Main + SupervisorJob()) }

    }

    val dataModule = module {
        single<OkHttpClient> {
            val authInterceptor = Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(
                            "Authorization",
                            "Discogs key=${appConfig.discogsConsumerKey}, secret=${appConfig.discogsConsumerSecret}"
                        )
                        .build()
                )
            }
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
        }

        single<DiscoService> {
            Retrofit.Builder()
                .client(get())
                .baseUrl(appConfig.discogsBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DiscoService::class.java)
        }

        single<DiscoServiceMapper> { DiscoServiceMapperImpl() }

        single<DiscoCache> { DiscoCacheImpl() }

        single<DiscoRepository> { DiscoRepositoryImpl(get(), get(), get()) }

    }

    val useCaseModule = module {
        single<GetArtistSearchResultsUseCase> { GetArtistSearchResultsUseCaseImpl(get()) }

        single<GetReleaseSearchResultsUseCase> { GetReleaseSearchResultsUseCaseImpl(get()) }

        single<GetArtistDetailsUseCase> { GetArtistDetailsUseCaseImpl(get()) }

        single<GetArtistReleasesUseCase> { GetArtistReleasesUseCaseImpl(get()) }

        single<GetReleaseDetailsUseCase> { GetReleaseDetailsUseCaseImpl(get()) }
    }


    val presentationModule = module {

        single<ImageLoader> { PicassoImageLoaderImpl() }

        single<ViewUtils> { ViewUtilsImpl() }

        viewModel<SearchViewModel> { SearchViewModel(get()) }

        viewModel<ArtistDetailsViewModel> { (artistId: String) -> ArtistDetailsViewModel(artistId, get()) }

        viewModel<ReleaseListViewModel> { (artist: Artist) -> ReleaseListViewModel(artist, get()) }

        viewModel<ReleaseDetailsViewModel> { (releaseId: String) -> ReleaseDetailsViewModel(releaseId, get()) }
    }
}

