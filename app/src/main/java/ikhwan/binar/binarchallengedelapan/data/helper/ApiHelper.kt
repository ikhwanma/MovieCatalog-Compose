package ikhwan.binar.binarchallengedelapan.data.helper

import ikhwan.binar.binarchallengedelapan.data.networking.ApiService
import javax.inject.Inject
import javax.inject.Named

class ApiHelper @Inject constructor(
    @Named("Movie") private val apiServiceMovie: ApiService,
    @Named("User") private val apiServiceUser: ApiService
) {
    suspend fun getPopularMovies(apiKey: String) = apiServiceMovie.getPopularMovie(apiKey)

    suspend fun getUser(email: String) = apiServiceUser.getUser(email)
}