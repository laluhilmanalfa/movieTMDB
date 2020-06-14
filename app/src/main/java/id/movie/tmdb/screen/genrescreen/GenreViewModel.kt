package id.movie.tmdb.screen.genrescreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.movie.tmdb.model.GenreModel
import id.movie.tmdb.repository.MovieRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GenreViewModel (val movieRepo : MovieRepository) : ViewModel() {
    val listGenreMutable : MutableLiveData<ArrayList<GenreModel>> = MutableLiveData(ArrayList())
    val isShowLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isRequestError : MutableLiveData<Boolean> = MutableLiveData(false)

    private var compositeDisposable  = CompositeDisposable()

    init {
        loadListGenre()
    }

    fun loadListGenre (){
        isShowLoading.value = true
        compositeDisposable.add(
            movieRepo.getListGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe({ t -> listGenreMutable.value = t.genres
                    isShowLoading.value = false
                }, {
                    isShowLoading.value = false
                    isRequestError.value = true

                })
        )
    }

}