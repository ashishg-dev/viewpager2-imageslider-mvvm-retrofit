package com.devashish.trendingmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devashish.trendingmovies.R
import com.devashish.trendingmovies.databinding.LsvCastBinding
import com.devashish.trendingmovies.model.CastModel

class CastAdapter(
    private val data: List<CastModel>,
) : RecyclerView.Adapter<CastAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val dataBinding: LsvCastBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(item: CastModel) {
            dataBinding.model = item
            dataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: LsvCastBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.lsv_cast,
            parent, false
        )

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}