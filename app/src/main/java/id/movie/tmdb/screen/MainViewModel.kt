package id.movie.tmdb.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.movie.tmdb.model.MovieModel
import id.movie.tmdb.repository.MovieRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val movieRepo : MovieRepository) : ViewModel() {

    val listMovieMutable : MutableLiveData<ArrayList<MovieModel>> = MutableLiveData(ArrayList())
    val isLoadingLoadmore : MutableLiveData<Boolean> = MutableLiveData(false)
    val isInitialLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isRequestError : MutableLiveData<Boolean> = MutableLiveData(false)
    val listMovieModel : ArrayList<MovieModel> = ArrayList()

    private var moviePage = 1
    private var genreId = 0
    private var compositeDisposable  = CompositeDisposable()

     fun loadListMovie(){
        showLoading(true)
         compositeDisposable.add(
            movieRepo.getListMovie(moviePage, genreId)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe({ t -> listMovieMutable.value = t.results
                                listMovieModel.addAll(t.results)
                                showLoading(false)
                                moviePage++
                }, {
                    showLoading(false)
                    isRequestError.value = true
                })
        )
    }

    fun handleLoadMore(){
        if(!isLoadingLoadmore.value!!){
           loadListMovie()
        }
    }

    fun setGenre( idGenre : Int){
        genreId = idGenre
        loadListMovie()

    }

    private fun showLoading( isShowLoading : Boolean){
        if(moviePage == 1 )
            isInitialLoading.value = isShowLoading
        else
            isLoadingLoadmore.value = isShowLoading

    }
}