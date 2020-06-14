package id.movie.tmdb.screen.genrescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.movie.tmdb.R
import id.movie.tmdb.adapter.GenreListAdapter
import id.movie.tmdb.adapter.listener.ItemSelectedListener
import id.movie.tmdb.databinding.ActivityGenreBinding
import id.movie.tmdb.model.GenreModel
import id.movie.tmdb.screen.MainActivity
import org.koin.android.ext.android.inject


class GenreActivity : AppCompatActivity() {


    private val viewModel : GenreViewModel by inject()
    lateinit var binding : ActivityGenreBinding
    private lateinit var adapter : GenreListAdapter
    private var genreSelectedListener =  object  :  ItemSelectedListener{
        override fun onItemSelectedListener(itemSelected: Object) {
            startActivity(MainActivity.createIntentActivity(this@GenreActivity, (itemSelected as GenreModel).id))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityGenreBinding>(this, R.layout.activity_genre)

        with(binding){
            lifecycleOwner = this@GenreActivity
            viewModel = this.viewModel
        }

        with(viewModel){
            listGenreMutable.observe(this@GenreActivity, Observer { setupGenreRecyclerView(it) })
            isShowLoading.observe(this@GenreActivity, Observer { showLoading( it) })
            isRequestError.observe(this@GenreActivity, Observer { showError( it) })

        }

    }


    private fun showLoading(isShowLoading : Boolean){
        if(isShowLoading) {
            binding.genreCenterLoading.startShimmerAnimation()
            binding.genreCenterLoading.visibility = View.VISIBLE
        }
        else {
            binding.genreCenterLoading.visibility = View.GONE
            binding.genreCenterLoading.stopShimmerAnimation()
        }
    }

    private fun setupGenreRecyclerView(listGenreMovie : ArrayList<GenreModel>){
        adapter = GenreListAdapter(listGenreMovie, itemSelectedListener = genreSelectedListener)
        binding.genreListMovieRecycler.layoutManager = GridLayoutManager(this, 2)
        binding.genreListMovieRecycler.itemAnimator = DefaultItemAnimator()
        binding.genreListMovieRecycler.adapter = adapter
    }


    @SuppressLint("WrongConstant")
    private fun showError(isError : Boolean){
        if(isError) {
            val snackbar = Snackbar
                .make(
                    binding.genreCenterLoading,
                    "Something Error Happen, Please Try Again",
                    Snackbar.LENGTH_INDEFINITE
                )
                .setAction("OK") {
                    viewModel.loadListGenre()
                }

            snackbar.show()
        }
    }

}