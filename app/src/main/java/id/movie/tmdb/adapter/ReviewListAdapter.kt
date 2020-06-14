package id.movie.tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.movie.tmdb.databinding.ReviewAdapterBinding
import id.movie.tmdb.model.ReviewModel

class ReviewListAdapter(val listReview : ArrayList<ReviewModel>)  : RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ReviewAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listReview.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listReview.get(position))
    }

    inner class ReviewViewHolder(val binding : ReviewAdapterBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind( data: ReviewModel) {
            binding.data = data
            binding.executePendingBindings()

        }

    }

}