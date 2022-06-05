package ikhwan.binar.binarchallengedelapan.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.BuildConfig
import ikhwan.binar.binarchallengedelapan.data.utils.Status.*
import ikhwan.binar.binarchallengedelapan.model.credit.Cast
import ikhwan.binar.binarchallengedelapan.model.detailmovie.GetDetailMovieResponse
import ikhwan.binar.binarchallengedelapan.view.screen.DetailScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelMovie


@SuppressLint("MutableCollectionMutableState")
@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private val viewModelMovie : ViewModelMovie by viewModels()

    private val listDetail: MutableList<GetDetailMovieResponse> by mutableStateOf(mutableListOf())
    private val listCast: MutableList<Cast> by mutableStateOf(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiKey = BuildConfig.TMDB_KEY
        viewModelMovie.apiKey.value = apiKey
        val id = intent.getIntExtra(INTENT_ID, 0)
        Log.d("iniListDetails", id.toString())
        viewModelMovie.getDetailMovie(id).observe(this){
            when(it.status){
                SUCCESS -> {
                    listDetail.add(it.data!!)
                    viewModelMovie.getCreditMovie(id).observe(this){ cred ->
                        when(cred.status){
                            SUCCESS -> {
                                listCast.clear()
                                listCast.addAll(cred.data!!.cast)
                                setLayout()
                            }
                            ERROR -> {
                                Log.d("errMsg", it.status.toString())
                            }
                            LOADING -> {
                                Log.d("loading", "lagi loading")
                            }
                        }
                    }
                }
                ERROR -> {
                    Log.d("errMsg", it.status.toString())
                }
                LOADING -> {
                    Log.d("loading", "lagi loading")
                }
            }
        }
    }

    private fun setLayout(){
        setContent {
            BinarChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (listDetail.isNotEmpty()){
                        DetailScreen(listDetail[0], listCast)
                    }
                }
            }
        }
    }

    companion object{
        const val INTENT_ID = "id"
    }
}


