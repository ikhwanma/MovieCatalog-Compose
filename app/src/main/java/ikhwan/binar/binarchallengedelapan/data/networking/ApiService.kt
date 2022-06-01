package ikhwan.binar.binarchallengedelapan.data.networking

import ikhwan.binar.binarchallengelima.model.popularmovie.GetPopularMovieResponse
import ikhwan.binar.binarchallengelima.model.users.GetUserResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String
    ): GetPopularMovieResponse

    @GET("/users")
    suspend fun getUser(
        @Query("email") email: String
    ) : List<GetUserResponseItem>
}