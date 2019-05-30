package com.bachelor.arty.logmeinassignment.presentation.feed_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bachelor.arty.logmeinassignment.R
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import kotlinx.android.synthetic.main.item_feed_source.view.*

class FeedListAdapter (private val itemClickListener: (RssFeedSource) -> Unit) : RecyclerView.Adapter<FeedListAdapter.RssFeedSourceViewHolder>() {


    var data : List<RssFeedSource> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssFeedSourceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed_source, parent, false)

        // val layoutParams = itemBinding.root.layoutParams
        // layoutParams.width = parent.width / 3
        // itemBinding.root.layoutParams = layoutParams
        return RssFeedSourceViewHolder(view, itemClickListener)
    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RssFeedSourceViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateData(sources: List<RssFeedSource>) {
        data = sources
        notifyDataSetChanged()
    }

    class RssFeedSourceViewHolder(private val view: View,
                                  private var itemClickListener: (RssFeedSource) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bind(item: RssFeedSource) {
            view.setOnClickListener  { itemClickListener(item) }
            view.tv_feed_title.text = item.name
            view.tv_feed_descrpition.text = view.context.getString(R.string.default_feed_source_description)
        }

    }


}