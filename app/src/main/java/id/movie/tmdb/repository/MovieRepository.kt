package id.movie.tmdb.repository
import id.movie.tmdb.model.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieRepository {

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false")
    fun getListMovie(@Query("page") page :  Int,
                     @Query("with_genres") with_genres :  Int): Observable<MovieListModel>


    @GET("genre/movie/list?api_key=387344049ef5720406cb6aa63f9aa49d&language=en-US")
    fun getListGenre(): Observable<GenreListModel>

    @GET("movie/{movie_id}/reviews?api_key=387344049ef5720406cb6aa63f9aa49d&language=en-US&page=1")
    fun getListReview(@Path("movie_id")  movie_id : String,
                      @Query("page") page :  Int): Observable<ReviewListModel>

    @GET("movie/{movie_id}/videos?")
    fun getListVideo(@Path("movie_id")  movie_id : String) : Observable<MovieVideoListModel>

    @GET("movie/{movie_id}?api_key=387344049ef5720406cb6aa63f9aa49d&language=en-US")
    fun getDetailMovie(@Path("movie_id")  movie_id : String) : Observable<MovieDetailModel>


}