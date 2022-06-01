package ikhwan.binar.binarchallengedelapan.data.utils

import ikhwan.binar.binarchallengedelapan.data.helper.ApiHelper
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovie(apiKey: String) = apiHelper.getPopularMovies(apiKey)
    suspend fun getNowPlayingMovie(apiKey: String) = apiHelper.getNowPlayingMovies(apiKey)

    suspend fun getUser(email : String) = apiHelper.getUser(email)
    suspend fun addUsers(user: PostUserResponse) = apiHelper.addUsers(user)

}