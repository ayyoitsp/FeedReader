package com.ayyoitsp.discogs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.ayyoitsp.discogs.interactor.*
import com.ayyoitsp.discogs.presentation.search.SearchFragment
import com.ayyoitsp.discogs.navigation.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navController = findNavController(
//            findViewById(R.id.nav_host_fragment)
//            )
//        setupWithNavController(navController)

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

//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.root_layout, ArtistDetailsFragment.newInstance("125246"))
//            .commit()


//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.root_layout, SearchFragment.newInstance())
//            .commit()


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}
