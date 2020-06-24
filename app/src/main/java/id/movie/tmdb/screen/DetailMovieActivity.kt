//package id.movie.tmdb.screen
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.Gravity
//import android.view.View
//import androidx.annotation.NonNull
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.google.android.material.chip.Chip
//import com.google.android.material.snackbar.Snackbar
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
//import id.movie.tmdb.R
//import id.movie.tmdb.adapter.ReviewListAdapter
//import id.movie.tmdb.model.GenreModel
//import id.movie.tmdb.model.MovieDetailModel
//import id.movie.tmdb.model.MovieVideoModel
//import id.movie.tmdb.model.ReviewModel
//import org.koin.android.ext.android.inject
//
//class DetailMovieActivity : AppCompatActivity() {
//
//    private val viewModel : DetailMovieViewModel by inject()
////    lateinit var binding : ActivityDetailMovieBinding
//    lateinit var adapter : ReviewListAdapter
//
//    companion  object {
//        lateinit var snackbar : Snackbar
//        fun createIntentActivity(context : Context, movieId : Int): Intent {
//
//            var intentToMainActivity = Intent(context, DetailMovieActivity::class.java)
//            intentToMainActivity.putExtra("movieId", movieId)
//            return intentToMainActivity
//        }
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        binding = DataBindingUtil.setContentView(this@DetailMovieActivity, R.layout.activity_detail_movie)
////
////        with(binding){
////            lifecycleOwner = this@DetailMovieActivity
////            viewModel = this@DetailMovieActivity.viewModel
////        }
//        with(viewModel){
//            isLoadingVideoContent.observe(this@DetailMovieActivity, Observer { showShimmerVideo(it) })
//            isLoadingReviewContent.observe(this@DetailMovieActivity, Observer { showShimmerReview(it) })
//            isLoadingMovieContent.observe(this@DetailMovieActivity, Observer { showShimmerMovie(it) })
//
//            isRequestMovieError.observe(this@DetailMovieActivity, Observer { showError(it) })
//            isRequestReviewError.observe(this@DetailMovieActivity, Observer { showError(it) })
//            isRequestVideoError.observe(this@DetailMovieActivity, Observer { showError(it) })
//
//            movieDetailModel.observe(this@DetailMovieActivity, Observer { updateData(  it) })
//            listReviewModel.observe(this@DetailMovieActivity, Observer { setUpReviewRecyclerView(it) })
//            listVideo.observe(this@DetailMovieActivity, Observer { loadVideo(it) })
//        }
//        setupSnackBar()
//        getIntentExtra()
//    }
//
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
//            .with(this@DetailMovieActivity)
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
//            binding.shimmerVideoContainer.startShimmerAnimation()
//            binding.shimmerVideoContainer.visibility = View.VISIBLE
//            binding.youtubePlayerView.visibility = View.GONE
//        }
//        else {
//            binding.shimmerVideoContainer.stopShimmerAnimation()
//            binding.shimmerVideoContainer.visibility = View.GONE
//            binding.youtubePlayerView.visibility = View.VISIBLE
//        }
//    }
//
//    private fun showShimmerMovie(isShowShimmer : Boolean){
//        if(isShowShimmer){
//            binding.shimmerMovieContainer.startShimmerAnimation()
//            binding.shimmerMovieContainer.visibility = View.VISIBLE
//            showComponentDetailMovie(View.GONE)
//        }
//        else {
//            binding.shimmerMovieContainer.stopShimmerAnimation()
//            binding.shimmerMovieContainer.visibility = View.GONE
//            showComponentDetailMovie(View.VISIBLE)
//
//        }
//    }
//
//    private fun showShimmerReview(isShowShimmer : Boolean){
//        if(isShowShimmer){
//            binding.shimmerReviewContainer.startShimmerAnimation()
//            binding.shimmerReviewContainer.visibility = View.VISIBLE
//            binding.detailMovieCommentTitle.visibility = View.GONE
//        }
//        else {
//            binding.shimmerReviewContainer.stopShimmerAnimation()
//            binding.shimmerReviewContainer.visibility = View.GONE
//            binding.detailMovieCommentTitle.visibility = View.VISIBLE
//        }
//    }
//
//    private fun showComponentDetailMovie(isShowComponen : Int){
//        binding.detailMovieGenreTitle.visibility = isShowComponen
//        binding.movieDetailRatingImage.visibility = isShowComponen
//        binding.movieDetailVoteImage.visibility = isShowComponen
//        binding.movieDetailPopularityImage.visibility = isShowComponen
//        binding.movieDetailChipGroup.visibility = isShowComponen
//        binding.movieDetailRatingTextView.visibility = isShowComponen
//        binding.movieDetailPopularityTextView.visibility = isShowComponen
//        binding.movieDetailVoteTextView.visibility = isShowComponen
//        binding.detailMovieYear.visibility = isShowComponen
//    }
//
//    @SuppressLint("WrongConstant")
//    private fun setupSnackBar(){
//        snackbar = Snackbar
//            .make(
//                binding.movieDetailRecycler,
//                "Something Error Happen, Please Try Again",
//                Snackbar.LENGTH_INDEFINITE
//            )
//            .setAction("OK") {
//                viewModel.reload()
//            }
//    }
//    private fun showError(isError : Boolean){
//        if(isError && !snackbar.isShown) {
//            snackbar.show()
//        }
//    }
//
//}