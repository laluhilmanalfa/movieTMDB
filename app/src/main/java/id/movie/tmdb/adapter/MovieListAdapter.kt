package id.movie.tmdb.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.movie.tmdb.adapter.listener.ItemSelectedListener

import id.movie.tmdb.databinding.MovieAdapterBinding
import id.movie.tmdb.model.MovieModel

class MovieListAdapter(val listMovie  : ArrayList<MovieModel>, val itemSelectedListener : ItemSelectedListener)  : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false), itemSelectedListener)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie.get(position))
    }

    inner class MovieViewHolder(val binding : MovieAdapterBinding, val itemSelectedListener : ItemSelectedListener) : RecyclerView.ViewHolder(binding.root){

        fun bind( data: MovieModel) {
            binding.data = data
            Glide
                .with(binding.movieAdapterImageThumbnail.context)
                .load("https://image.tmdb.org/t/p/w200/"+data.poster_path)
                .fitCenter()
                .into(binding.movieAdapterImageThumbnail);
            binding.executePendingBindings()
            binding.genreAdapterContainerView.setOnClickListener {
                itemSelectedListener.onItemSelectedListener(data as Object)
            }
        }

    }

}