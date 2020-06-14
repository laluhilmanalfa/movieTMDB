package id.movie.tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.movie.tmdb.adapter.listener.ItemSelectedListener
import id.movie.tmdb.databinding.GenreAdapterBinding
import id.movie.tmdb.model.GenreModel

class GenreListAdapter(val listGenre : ArrayList<GenreModel>, val itemSelectedListener : ItemSelectedListener)  : RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(GenreAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false), itemSelectedListener)
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listGenre.get(position))
    }

    inner class GenreViewHolder(val binding : GenreAdapterBinding, val itemSelectedListener : ItemSelectedListener) : RecyclerView.ViewHolder(binding.root){


        fun bind( data: GenreModel) {
            binding.data = data
            binding.executePendingBindings()

            binding.genreAdapterContainerView.setOnClickListener {
                itemSelectedListener.onItemSelectedListener(data as Object)
            }
        }

    }

}