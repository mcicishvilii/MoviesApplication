package com.example.moviesapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.SingleMovieLayoutBinding
import com.example.movieswatchlist.Models.Responses.PopularResponse


class MoviesAdapter: PagingDataAdapter<PopularResponse.Result1, MoviesAdapter.MoviesViewHolder>(DiffCallBack()) {

    private lateinit var itemClickListener: (PopularResponse.Result1, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MoviesViewHolder {
        val binding =
            SingleMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindData()
    }


    inner class MoviesViewHolder(private val binding: SingleMovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: PopularResponse.Result1? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvMovieName.text = model?.originalTitle.toString()
                tvAverageRating.text = "Average votes: ${model?.voteAverage.toString()}"
                tvRating.text = "Rating: ${model?.voteCount.toString()}"
                tvReleaseDate.text = model?.releaseDate.toString()

                Glide.with(this.ivImagePoster)
                    .load("https://image.tmdb.org/t/p/w500${model?.posterPath}")
                    .into(ivImagePoster)
            }


            binding.tvMovieName.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<PopularResponse.Result1>() {
        override fun areItemsTheSame(oldItem: PopularResponse.Result1, newItem: PopularResponse.Result1): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularResponse.Result1, newItem: PopularResponse.Result1
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(clickListener: (PopularResponse.Result1, Int) -> Unit) {
        itemClickListener = clickListener
    }
}