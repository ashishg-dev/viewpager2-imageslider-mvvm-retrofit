package com.devashish.trendingmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devashish.trendingmovies.R
import com.devashish.trendingmovies.databinding.LsvPagerBinding
import com.devashish.trendingmovies.model.TrendingMoviesModel

class SliderAdapter(
    private val data: List<TrendingMoviesModel>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<SliderAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val dataBinding: LsvPagerBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(item: TrendingMoviesModel, listener: OnItemClickListener) {
            dataBinding.model = item
            dataBinding.listener = listener
            dataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SliderAdapter.ItemViewHolder {
        val view: LsvPagerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.lsv_pager,
            parent, false
        )

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.ItemViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClicked(model: TrendingMoviesModel)
    }

}