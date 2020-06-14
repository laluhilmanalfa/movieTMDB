//package id.movie.tmdb.screen.detail
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.view.Gravity
//import android.view.View
//import androidx.annotation.NonNull
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.google.android.material.chip.Chip
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
//import id.movie.tmdb.R
//import id.movie.tmdb.adapter.ReviewListAdapter
//import id.movie.tmdb.databinding.ActivityMovieDetailBinding
//import id.movie.tmdb.model.GenreModel
//import id.movie.tmdb.model.MovieDetailModel
//import id.movie.tmdb.model.MovieVideoModel
//import id.movie.tmdb.model.ReviewModel
//import org.koin.android.ext.android.inject
//
//
//class MovieDetailActivity : AppCompatActivity() {
//
//    private val viewModel : MovieDetailViewModel by inject()
//    lateinit var binding : ActivityMovieDetailBinding
//    lateinit var adapter : ReviewListAdapter
//
//    companion  object {
//        fun createIntentActivity(context : Context, movieId : Int): Intent {
//
//            var intentToMainActivity = Intent(context, MovieDetailActivity::class.java)
//            intentToMainActivity.putExtra("movieId", movieId)
//            return intentToMainActivity
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
//        with(binding){
//            lifecycleOwner = this@MovieDetailActivity
//            viewModel = this@MovieDetailActivity.viewModel
//        }
//        with(viewModel){
//            isLoadingVideoContent.observe(this@MovieDetailActivity, Observer { showShimmerVideo(it) })
//            isLoadingReviewContent.observe(this@MovieDetailActivity, Observer { showShimmerReview(it) })
//            isLoadingMovieContent.observe(this@MovieDetailActivity, Observer { showShimmerMovie(it) })
//
//            isRequestMovieError.observe(this@MovieDetailActivity, Observer { handleErroeMovie(it) })
//            isRequestReviewError.observe(this@MovieDetailActivity, Observer { handleErrorReview(it) })
//            isRequestVideoError.observe(this@MovieDetailActivity, Observer { handleErrorVideo(it) })
//
//            movieDetailModel.observe(this@MovieDetailActivity, Observer { updateData(  it) })
//            listReviewModel.observe(this@MovieDetailActivity, Observer { setUpReviewRecyclerView(it) })
//            listVideo.observe(this@MovieDetailActivity, Observer { loadVideo(it) })
//        }
//
//        binding.shimmerVideoContainer.startShimmerAnimation()
//        binding.shimmerMovieContainer.startShimmerAnimation()
//        binding.shimmerReviewContainer.startShimmerAnimation()
//        getIntentExtra()
//    }
//
//    private fun getIntentExtra(){
//        var genreId = intent.getIntExtra("movieId", 0)
//        viewModel.setMovieId(genreId)
//    }
//
//    private fun updateData(movieDetailModel: MovieDetailModel){
//        binding.detailMovieTitle.text = movieDetailModel.original_title
//        if(movieDetailModel.release_date.length>0)
//            binding.detailMovieYear.text = movieDetailModel.release_date.substring(0,4)
//        binding.detailMovieOverview.text = movieDetailModel.overview
//        Glide
//            .with(this@MovieDetailActivity)
//            .load("https://image.tmdb.org/t/p/w200/"+movieDetailModel.poster_path)
//            .fitCenter()
//            .into(binding.detailMoviePoster);
//
//        binding.movieDetailRatingTextView.text = movieDetailModel.vote_average.toString()
//        binding.movieDetailVoteTextView.text = movieDetailModel.vote_count.toString()
//        binding.movieDetailPopularityTextView.text = movieDetailModel.popularity.toString()
//
//        createGenreList(movieDetailModel.genres)
//    }
//
//
//    private fun setUpReviewRecyclerView(listReview : ArrayList<ReviewModel>){
//        adapter = ReviewListAdapter(listReview)
//        binding.movieDetailRecycler.layoutManager = LinearLayoutManager(this)
//        binding.movieDetailRecycler.itemAnimator = DefaultItemAnimator()
//        binding.movieDetailRecycler.adapter = adapter
//
//    }
//
//    private fun createGenreList(listGenre : ArrayList<GenreModel>){
//        for(genremodel in listGenre){
//            val chip = Chip(this)
//            chip.text = "    " + genremodel.name + "   "
//            chip.isClickable = false
//            chip.isCheckable = false
//            chip.gravity = Gravity.CENTER
//            binding.movieDetailChipGroup.addView(chip)
//        }
//    }
//
//
//    private fun loadVideo(listVideoModel : ArrayList<MovieVideoModel>){
//        if(listVideoModel.size>0){
//            val youTubePlayerView = binding.youtubePlayerView
//            lifecycle.addObserver(youTubePlayerView)
//
//            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
//
//                    youTubePlayer.loadVideo(listVideoModel.get(0).key, 0f)
//                }
//            })
//        }
//    }
//
//    private fun showShimmerVideo(isShowShimmer : Boolean){
//        if(isShowShimmer){
//            binding.shimmerVideoContainer.visibility = View.VISIBLE
//            binding.youtubePlayerView.visibility = View.GONE
//        }
//        else {
//            binding.shimmerVideoContainer.visibility = View.GONE
//            binding.youtubePlayerView.visibility = View.VISIBLE
//        }
//    }
//
//    private fun showShimmerMovie(isShowShimmer : Boolean){
//        if(isShowShimmer){
//            binding.shimmerMovieContainer.visibility = View.VISIBLE
//            showComponentDetailMovie(View.GONE)
//        }
//        else {
//            binding.shimmerMovieContainer.visibility = View.GONE
//            showComponentDetailMovie(View.VISIBLE)
//
//        }
//    }
//
//    private fun showShimmerReview(isShowShimmer : Boolean){
//        if(isShowShimmer){
//            binding.shimmerReviewContainer.visibility = View.VISIBLE
//        }
//        else {
//            binding.shimmerReviewContainer.visibility = View.GONE
//        }
//    }
//
//    private fun handleErrorVideo(isError : Boolean){
//        if(isError){
//            binding.movieDetailReloadVideo.visibility = View.VISIBLE
//            binding.youtubePlayerView.visibility = View.GONE
//        }
//        else {
//            binding.movieDetailReloadVideo.visibility = View.GONE
//            binding.youtubePlayerView.visibility = View.VISIBLE
//
//        }
//    }
//
//    private fun handleErroeMovie(isError : Boolean){
//        if(isError){
//            binding.movieDetailReloadMovie.visibility = View.VISIBLE
//            showComponentDetailMovie(View.GONE)
//        }
//        else {
//            binding.movieDetailReloadMovie.visibility = View.GONE
//            showComponentDetailMovie(View.VISIBLE)
//
//        }
//    }
//
//    private fun handleErrorReview(isError : Boolean){
//        if(isError){
//            binding.movieDetailReloadReview.visibility = View.VISIBLE
//        }
//        else {
//            binding.movieDetailReloadReview.visibility = View.GONE
//
//        }
//    }
//
//
//    private fun showComponentDetailMovie(isShowComponen : Int){
//            binding.detailMovieCommentTitle.visibility = isShowComponen
//            binding.detailMovieGenreTitle.visibility = isShowComponen
//            binding.movieDetailRatingImage.visibility = isShowComponen
//            binding.movieDetailVoteImage.visibility = isShowComponen
//            binding.movieDetailPopularityImage.visibility = isShowComponen
//    }
//
//}