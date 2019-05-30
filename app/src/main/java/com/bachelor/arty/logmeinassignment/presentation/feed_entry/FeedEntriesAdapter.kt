package com.bachelor.arty.logmeinassignment.presentation.feed_entry

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bachelor.arty.logmeinassignment.R
import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import kotlinx.android.synthetic.main.item_feed_article.view.*

class FeedEntriesAdapter(private val itemClickListener: (ArticleEntity) -> Unit) :
    RecyclerView.Adapter<FeedEntriesAdapter.FeedEntryViewHolder>() {

    var data: List<ArticleEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedEntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed_article, parent, false)
        return FeedEntryViewHolder(view, itemClickListener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FeedEntryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateData(articles: List<ArticleEntity>) {
        data = articles
        notifyDataSetChanged()
    }

    class FeedEntryViewHolder(
        private val view: View,
        private var itemClickListener: (ArticleEntity) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: ArticleEntity) {
            view.setOnClickListener { itemClickListener(item) }
            view.tv_feed_article_title.text = parseHtml(item.title)
            view.tv_feed_article_description.text =  parseHtml(item.desc)
        }

        // Source: https://stackoverflow.com/questions/2116162/how-to-display-html-in-textview
        private fun parseHtml(htmlCode: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(htmlCode, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(htmlCode)
            }
        }

    }


}