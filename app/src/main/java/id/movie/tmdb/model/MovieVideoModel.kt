package id.movie.tmdb.model

import com.google.gson.annotations.SerializedName

data class MovieVideoModel(

    @SerializedName("id")
    var id : String = "",
    @SerializedName("key")
    var key :String = "",
    @SerializedName("name")
    var name :String = "",
    @SerializedName("site")
    var site :String = "",
    @SerializedName("size")
    var size : Int  = 0 ,
    @SerializedName("type")
    var type : String = ""
)