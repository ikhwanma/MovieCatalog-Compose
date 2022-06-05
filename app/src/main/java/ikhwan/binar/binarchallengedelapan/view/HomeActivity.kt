package ikhwan.binar.binarchallengedelapan.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.BuildConfig
import ikhwan.binar.binarchallengedelapan.data.utils.Status.*
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.view.screen.HomeScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelMovie
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@SuppressLint("MutableCollectionMutableState")
@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val listPopular: MutableList<Result> by mutableStateOf(mutableListOf())
    private val listNowPlaying: MutableList<Result> by mutableStateOf(mutableListOf())

    private val viewModelMovie: ViewModelMovie by viewModels()
    private val viewModelUser: ViewModelUser by viewModels()
    private val viewModelState: ViewModelState by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val apiKey = BuildConfig.TMDB_KEY

                    viewModelUser.getId().observe(this) {
                        viewModelUser.setIdState(it)
                    }

                    viewModelMovie.apiKey.value = apiKey
                    HomeScreen(viewModelUser, viewModelState, listPopular, listNowPlaying)

                }
            }
        }

        getPopular()
        getNowPlaying()
    }

    private fun getNowPlaying() {
        viewModelMovie.getNowPlaying().observe(this) {
            when (it.status) {
                SUCCESS -> {
                    it.data.let { data ->
                        listNowPlaying.clear()
                        listNowPlaying.addAll(data!!.resultNows)
                    }
                }
                ERROR -> {
                    Log.d("errMsg", it.message.toString())
                    getNowPlaying()
                }
                LOADING -> {
                    Log.d("loading", "lagi loading")
                }
            }
        }
    }

    private fun getPopular(){
        viewModelMovie.getPopularMovie().observe(this) {
            when (it.status) {
                SUCCESS -> {
                    it.data.let { data ->
                        listPopular.clear()
                        listPopular.addAll(data!!.results)
                    }

                }
                ERROR -> {
                    Log.d("errMsg", it.message.toString())
                    getPopular()
                }
                LOADING -> {
                    Log.d("loading", "lagi loading")
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
