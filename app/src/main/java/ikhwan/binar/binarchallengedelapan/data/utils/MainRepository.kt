package ikhwan.binar.binarchallengedelapan.data.utils

import ikhwan.binar.binarchallengedelapan.data.helper.ApiHelper
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovie(apiKey: String) = apiHelper.getPopularMovies(apiKey)
    suspend fun getNowPlayingMovie(apiKey: String) = apiHelper.getNowPlayingMovies(apiKey)
    suspend fun getDetailMovie(id: Int, apiKey: String) = apiHelper.getDetailMovie(id, apiKey)
    suspend fun getCreditMovie(id: Int, apiKey: String) = apiHelper.getCreditMovie(id, apiKey)
    suspend fun getSimilarMovie(id: Int, apiKey: String) = apiHelper.getSimilarMovie(id, apiKey)
    suspend fun getImageMovie(id: Int, apiKey: String) = apiHelper.getImageMovie(id, apiKey)

    suspend fun getUser(email : String) = apiHelper.getUser(email)
    suspend fun getAllUser() = apiHelper.getAllUser()
    suspend fun getUserId(id: String) = apiHelper.getUserId(id)
    suspend fun addUsers(user: PostUserResponse) = apiHelper.addUsers(user)

}