package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

data class MovieVideoListModel(
    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("results")
    var results : ArrayList<MovieVideoModel> = ArrayList()
)