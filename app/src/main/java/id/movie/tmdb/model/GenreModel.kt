package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("name")
    var name : String = ""
)