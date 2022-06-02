package ikhwan.binar.binarchallengedelapan.model.nowplaying


import com.google.gson.annotations.SerializedName
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result

data class GetNowPlayingResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultNows: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)