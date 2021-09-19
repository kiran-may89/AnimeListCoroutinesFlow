package com.code.assesment.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.assesment.R
import com.code.assesment.domain.model.Anime
import com.code.assesment.domain.model.Result
import kotlinx.android.synthetic.main.list_item.view.*


class AnimeAdapter(private var list: List<Result> = emptyList()) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    fun setItems(items: List<Result>) {
        list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(inflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun getItem(position: Int) = list[position]
    override fun getItemCount(): Int = list.size

    class AnimeViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Result) {
            root.title.text = item.title
            Glide.with(root.cover_page.context).load(item.imageUrl)
                .into(root.cover_page)


        }
    }
}
