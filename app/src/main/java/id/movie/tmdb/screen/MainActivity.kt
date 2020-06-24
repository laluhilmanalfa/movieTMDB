package id.movie.tmdb.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import id.movie.tmdb.R
import id.movie.tmdb.adapter.MovieListAdapter
import id.movie.tmdb.adapter.listener.ItemSelectedListener
import id.movie.tmdb.databinding.ActivityMainBinding
import id.movie.tmdb.model.MovieModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by inject()
    lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MovieListAdapter



    private var itemSelectedListener = object : ItemSelectedListener{
        override fun onItemSelectedListener(itemSelected: Object) {
            startActivity(DetailMovieActivity.createIntentActivity(this@MainActivity, (itemSelected as MovieModel).id))
        }

    }
    companion  object {
        fun createIntentActivity(context : Context, genreId : Int): Intent {

            var intentToMainActivity = Intent(context, MainActivity::class.java)
            intentToMainActivity.putExtra("genreId", genreId)
            return intentToMainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        with(binding){
            lifecycleOwner = this@MainActivity
            viewModel = this.viewModel
        }
        with(viewModel){
            listMovieMutable.observe(this@MainActivity, Observer { updateMovieList() })
            isInitialLoading.observe(this@MainActivity, Observer { showLoading( binding.mainCenterLoading, it) })
            isLoadingLoadmore.observe(this@MainActivity, Observer { showLoading(binding.mainBottomLoading, it) })
            isRequestError.observe(this@MainActivity, Observer { showError( it) })

        }
        getIntentExtra()
        setupMovieRecyclerView()
    }

    private fun getIntentExtra(){
        var genreId = intent.getIntExtra("genreId", 0)
        viewModel.setGenre(genreId)
    }

    private fun updateMovieList(){
        adapter.notifyDataSetChanged()
    }

    private fun  setupMovieRecyclerView(){
        adapter = MovieListAdapter(viewModel.listMovieModel, itemSelectedListener)
        var layoutManager = GridLayoutManager(this, 2)
        binding.mainListMovieRecycler.layoutManager = layoutManager
        binding.mainListMovieRecycler.itemAnimator = DefaultItemAnimator()
        binding.mainListMovieRecycler.adapter = adapter
        binding.mainListMovieRecycler.addOnScrollListener( object  : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var totalItemCount = layoutManager.itemCount-1
                var lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if(totalItemCount == lastVisibleItem){
                    viewModel.handleLoadMore()

                }
            }
        })
    }

    private fun showLoading(view : View ,isLoading : Boolean){
        if(isLoading) {
            binding.mainCenterLoading.startShimmerAnimation()
            view.visibility = View.VISIBLE
        }
        else {
            view.visibility = View.GONE
            binding.mainCenterLoading.stopShimmerAnimation()
        }
    }

    @SuppressLint("WrongConstant")
    private fun showError(isError : Boolean){
        if(isError) {
            val snackbar = Snackbar
                .make(
                    binding.mainBottomLoading,
                    "Something Error Happen, Please Try Again",
                    Snackbar.LENGTH_INDEFINITE
                )
                .setAction("OK") {
                    viewModel.loadListMovie()
                }

            snackbar.show()
        }
    }

}