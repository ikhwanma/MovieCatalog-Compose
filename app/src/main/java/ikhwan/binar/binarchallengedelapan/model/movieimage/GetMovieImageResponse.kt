package ikhwan.binar.binarchallengedelapan.model.movieimage


import com.google.gson.annotations.SerializedName

data class GetMovieImageResponse(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logos")
    val logos: List<Logo>,
    @SerializedName("posters")
    val posters: List<Poster>
)