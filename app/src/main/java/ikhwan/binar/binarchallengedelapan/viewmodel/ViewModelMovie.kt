package ikhwan.binar.binarchallengedelapan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.binarchallengedelapan.data.utils.MainRepository
import ikhwan.binar.binarchallengelima.model.popularmovie.ResultMovie
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelMovie @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    var getPopularResponse: List<ResultMovie> by mutableStateOf(listOf())
    var getNowPlayingResponse: List<ResultMovie> by mutableStateOf(listOf())

    fun getPopularMovie(apiKey : String) {
        viewModelScope.launch {
            val listPopular = mainRepository.getPopularMovie(apiKey)
            getPopularResponse = listPopular.resultMovies
        }
    }

    fun getNowPlayingMovie(apiKey: String){
        viewModelScope.launch {
            val listMovie = mainRepository.getNowPlayingMovie(apiKey)
            getNowPlayingResponse = listMovie.resultNows
        }
    }
}