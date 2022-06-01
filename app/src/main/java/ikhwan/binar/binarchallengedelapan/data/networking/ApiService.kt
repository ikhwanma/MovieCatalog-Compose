package ikhwan.binar.binarchallengedelapan.data.networking

import ikhwan.binar.binarchallengedelapan.model.nowplaying.GetNowPlayingResponse
import ikhwan.binar.binarchallengedelapan.model.popularmovie.GetPopularMovieResponse
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String
    ): GetPopularMovieResponse

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") api_key: String
    ): GetNowPlayingResponse

    //=====================User========================

    @GET("/users")
    suspend fun getUser(
        @Query("email") email: String
    ) : List<GetUserResponseItem>

    @POST("/users")
    suspend fun addUsers(
        @Body user : PostUserResponse
    ): GetUserResponseItem
}