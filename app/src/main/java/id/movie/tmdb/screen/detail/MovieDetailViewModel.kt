//package id.movie.tmdb.screen.detail
//
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import id.movie.tmdb.model.MovieDetailModel
//import id.movie.tmdb.model.MovieVideoModel
//import id.movie.tmdb.model.ReviewModel
//import id.movie.tmdb.repository.MovieRepository
//import io.reactivex.rxjava3.disposables.CompositeDisposable
//import io.reactivex.rxjava3.schedulers.Schedulers
//
//
//class MovieDetailViewModel(private val movieRepo : MovieRepository) : ViewModel() {
//
//    val isLoadingVideoContent : MutableLiveData<Boolean> = MutableLiveData(false)
//    val isLoadingReviewContent : MutableLiveData<Boolean> = MutableLiveData(false)
//    val isLoadingMovieContent : MutableLiveData<Boolean> = MutableLiveData(false)
//
//    val isRequestVideoError : MutableLiveData<Boolean> = MutableLiveData(false)
//    val isRequestMovieError : MutableLiveData<Boolean> = MutableLiveData(false)
//    val isRequestReviewError : MutableLiveData<Boolean> = MutableLiveData(false)
//
//
//    val movieDetailModel : MutableLiveData<MovieDetailModel> = MutableLiveData(MovieDetailModel())
//    val listReviewModel : MutableLiveData<ArrayList<ReviewModel>> = MutableLiveData(ArrayList())
//    val listVideo : MutableLiveData<ArrayList<MovieVideoModel>> = MutableLiveData(ArrayList())
//
//    private var compositeDisposable  = CompositeDisposable()
//    private var movieId =  0
//
//    fun loadDetailMovie(){
//        isLoadingMovieContent.value = true
//        compositeDisposable.add(
//            movieRepo.getDetailMovie(movie_id = movieId.toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
//                .subscribe({ it -> movieDetailModel.value = it
//                    isLoadingMovieContent.value = false
//                    isRequestMovieError.value = true
//                }, {
//                    isLoadingMovieContent.value = false
//                    isRequestMovieError.value = true
//
//                })
//        )
//    }
//
//
//    fun loadVideo(){
//        isLoadingVideoContent.value = true
//        compositeDisposable.add(
//            movieRepo.getListVideo(movie_id = movieId.toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
//                .subscribe({ it -> listVideo.value = it.results
//                    isLoadingVideoContent.value = false
//                    isRequestVideoError.value = false
//                }, {
//                    isRequestVideoError.value = true
//                    isLoadingVideoContent.value = false
//                })
//        )
//    }
//
//
//    fun loadReviews(){
//        isLoadingReviewContent.value = true
//        compositeDisposable.add(
//            movieRepo.getListReview(movie_id = movieId.toString(), page = 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
//                .subscribe({ it -> listReviewModel.value = it.results
//                    isLoadingReviewContent.value = false
//                    isRequestReviewError.value = false
//                }, {
//                    isRequestReviewError.value = true
//                    isLoadingReviewContent.value = false
//
//                })
//        )
//    }
//
//
//    fun setMovieId(idMovie : Int){
//        movieId = idMovie
//        loadDetailMovie()
//        loadVideo()
//        loadReviews()
//
//    }
//}