package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

class GenreListModel(
    @SerializedName("genres")
    var genres : ArrayList<GenreModel> = ArrayList<GenreModel>()
)