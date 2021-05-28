package com.ayyoitsp.discogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.interactor.*
import com.ayyoitsp.discogs.presentation.artist.ArtistDetailsFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    val getArtistSearchResultsUseCase: GetArtistSearchResultsUseCase by inject()
    val getReleaseSearchResultsUseCase: GetReleaseSearchResultsUseCase by inject()
    val getArtistDetailsUseCase: GetArtistDetailsUseCase by inject()
    val getArtistReleasesUseCase: GetArtistReleasesUseCase by inject()
    val getReleaseDetailsUseCase: GetReleaseDetailsUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        scope.launch {
//            getArtistSearchResultsUseCase
//                .execute(SearchRequestModel("nirvana", 1, 1))
//                .flowOn(Dispatchers.IO)
//                .collect {
//                    Log.e("****", it.toString())
//                }
//        }

//        scope.launch {
//            getReleaseSearchResultsUseCase
//                .execute(SearchRequest("nirvana", 1, 1))
//                .flowOn(Dispatchers.IO)
//                .collect {
//                    Log.e("****", it.toString())
//                }
//        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, ArtistDetailsFragment.newInstance("125246"))
            .commit()



    }


}
