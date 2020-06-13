package id.movie.tmdb.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import id.movie.tmdb.R
import id.movie.tmdb.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by inject()
    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        with(binding){
            lifecycleOwner = this@MainActivity
            viewModel = this.viewModel
        }

        with(viewModel){
        }
    }
}