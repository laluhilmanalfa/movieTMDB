package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    @SerializedName("adult")
    var adult : Boolean = false,
    @SerializedName("backdrop_path")
    var backdrop_path : String = "",
    @SerializedName("budget")
    var budget : Int = 0,
    @SerializedName("homepage")
    var homepage : String = "",
    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("imdb_id")
    var imdb_id : String = "",
    @SerializedName("original_language")
    var original_language : String = "",
    @SerializedName("original_title")
    var original_title : String = "",
    @SerializedName("overview")
    var overview : String = "",
    @SerializedName("popularity")
    var popularity : Double = 0.0,
    @SerializedName("poster_path")
    var poster_path : String = "",
    @SerializedName("release_date")
    var release_date : String = "2020",
    @SerializedName("revenue")
    var revenue : Int = 0,
    @SerializedName("runtime")
    var runtime : Int = 0,
    @SerializedName("status")
    var status : String = "",
    @SerializedName("tagline")
    var tagline : String = "",
    @SerializedName("title")
    var title : String = "",
    @SerializedName("video")
    var video : Boolean = false,
    @SerializedName("vote_average")
    var vote_average : Double = 0.0,
    @SerializedName("vote_count")
    var vote_count : Int = 0,
    @SerializedName("genres")
    var genres : ArrayList<GenreModel> = ArrayList()



)