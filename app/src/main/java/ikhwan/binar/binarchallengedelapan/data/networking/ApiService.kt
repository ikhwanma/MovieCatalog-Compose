package ikhwan.binar.binarchallengedelapan.data.networking

import ikhwan.binar.binarchallengedelapan.model.credit.GetCreditResponse
import ikhwan.binar.binarchallengedelapan.model.detailmovie.GetDetailMovieResponse
import ikhwan.binar.binarchallengedelapan.model.movieimage.GetMovieImageResponse
import ikhwan.binar.binarchallengedelapan.model.nowplaying.GetNowPlayingResponse
import ikhwan.binar.binarchallengedelapan.model.popularmovie.GetPopularMovieResponse
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import retrofit2.http.*

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String
    ): GetPopularMovieResponse

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") api_key: String
    ): GetNowPlayingResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): GetDetailMovieResponse

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCreditMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): GetCreditResponse

    @GET("/3/movie/{movie_id}/images")
    suspend fun getImageMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): GetMovieImageResponse

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): GetPopularMovieResponse

    //=====================User========================

    @GET("/users")
    suspend fun getUser(
        @Query("email") email: String
    ) : List<GetUserResponseItem>

    @GET("/users")
    suspend fun getAllUser() : List<GetUserResponseItem>

    @GET("/users/{id}")
    suspend fun getUserId(
        @Path("id") id: String
    ) : GetUserResponseItem

    @POST("/users")
    suspend fun addUsers(
        @Body user : PostUserResponse
    ): GetUserResponseItem
}