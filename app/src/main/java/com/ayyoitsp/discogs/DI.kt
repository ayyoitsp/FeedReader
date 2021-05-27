/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.data.disco.DiscoRepositoryImpl
import com.ayyoitsp.discogs.data.disco.cache.DiscoCache
import com.ayyoitsp.discogs.data.disco.cache.DiscoCacheImpl
import com.ayyoitsp.discogs.data.disco.service.DiscoService
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapper
import com.ayyoitsp.discogs.data.disco.service.DiscoServiceMapperImpl
import com.ayyoitsp.discogs.interactor.*
import com.ayyoitsp.discogs.presentation.details.ReleaseDetailsViewModel
import com.ayyoitsp.discogs.presentation.list.ReleaseListViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DI(val appConfig: AppConfig) {

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

    val viewModelModule = module {

        viewModel { ReleaseListViewModel(get()) }

        viewModel { ReleaseDetailsViewModel() }
    }
}

