package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

data class MovieListModel (
    @SerializedName("page")
    var page : Int = 0,
    @SerializedName("total_results")
    var total_results : Int = 0,
    @SerializedName("total_pages")
    var total_pages : Int = 0,
    @SerializedName("results")
    var results : ArrayList<MovieModel> = ArrayList()
)
