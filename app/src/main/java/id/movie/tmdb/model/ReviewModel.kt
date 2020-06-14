package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

data class ReviewModel(
    @SerializedName("author")
    var author : String = "",
    @SerializedName("content")
    var content : String = "",
    @SerializedName("id")
    var id : String = "",
    @SerializedName("url")
    var url :String = ""

)