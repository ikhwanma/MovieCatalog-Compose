package ikhwan.binar.binarchallengedelapan.data.helper

import ikhwan.binar.binarchallengedelapan.data.networking.ApiService
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import javax.inject.Inject
import javax.inject.Named

class ApiHelper @Inject constructor(
    @Named("Movie") private val apiServiceMovie: ApiService,
    @Named("User") private val apiServiceUser: ApiService
) {
    suspend fun getPopularMovies(apiKey: String) = apiServiceMovie.getPopularMovie(apiKey)
    suspend fun getNowPlayingMovies(apiKey: String) = apiServiceMovie.getNowPlayingMovie(apiKey)

    suspend fun getUser(email: String) = apiServiceUser.getUser(email)
    suspend fun addUsers(user: PostUserResponse) = apiServiceUser.addUsers(user)

}