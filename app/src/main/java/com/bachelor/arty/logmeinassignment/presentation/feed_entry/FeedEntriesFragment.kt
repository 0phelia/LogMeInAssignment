package com.bachelor.arty.logmeinassignment.presentation.feed_entry


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bachelor.arty.logmeinassignment.R
import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import com.bachelor.arty.logmeinassignment.presentation.NavigationActivity
import com.bachelor.arty.logmeinassignment.presentation.SELECTED_SOURCE
import org.koin.android.ext.android.inject

class FeedEntriesFragment : Fragment() {

    private val feedEntryViewModel: FeedEntryViewModel  by inject<FeedEntryViewModel>()

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var ad: FeedEntriesAdapter
    private lateinit var rv: RecyclerView
    private lateinit var pb: ProgressBar
    private lateinit var tvHeader: TextView
    private lateinit var selectedFeedSource: RssFeedSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val x = inflater.inflate(R.layout.fragment_feed_entry, container, false)
        setupData()
        setupUi(x)
        subscribeUi()

        return x
    }

    private fun setupData() {
        val rssSourceId = arguments?.getInt(SELECTED_SOURCE)!!
        selectedFeedSource = RssFeedSource.getSourceByName(rssSourceId)
    }


    private fun setupUi(x: View) {
        lm = LinearLayoutManager(this.context)
        ad = FeedEntriesAdapter { clickedRssEntry ->
            (activity as NavigationActivity).openBrowserWithUrl(clickedRssEntry.link)
        }
        rv = x.findViewById(R.id.rv_feed_entries)
        rv.apply {
            adapter = ad
            layoutManager = lm
        }
        pb = x.findViewById(R.id.pb_loading)
        tvHeader = x.findViewById(R.id.tv_header)
    }

    private fun subscribeUi() {
        feedEntryViewModel.feedEntryViewState.observe(this, Observer { state ->
            renderList(state.entries)
            tvHeader.text = "${selectedFeedSource.name} (${state.entries.size})"
            pb.visibility = if (state.loading) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun renderList(list: List<ArticleEntity>) {
        if (list.isNotEmpty()) ad.updateData(list)
    }

    override fun onResume() {
        super.onResume()
        feedEntryViewModel.getArticles(selectedFeedSource)
    }

}
