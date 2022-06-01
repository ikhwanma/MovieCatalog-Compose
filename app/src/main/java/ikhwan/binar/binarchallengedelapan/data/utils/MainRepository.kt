package ikhwan.binar.binarchallengedelapan.data.utils

import ikhwan.binar.binarchallengedelapan.data.helper.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovie(apiKey: String) = apiHelper.getPopularMovies(apiKey)

    suspend fun getUser(email : String) = apiHelper.getUser(email)

}