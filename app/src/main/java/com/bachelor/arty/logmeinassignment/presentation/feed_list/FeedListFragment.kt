package com.bachelor.arty.logmeinassignment.presentation.feed_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bachelor.arty.logmeinassignment.R
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import com.bachelor.arty.logmeinassignment.presentation.NavigationActivity
import org.koin.android.ext.android.inject

class FeedListFragment : Fragment() {
    private val feedListViewModel: FeedListViewModel  by inject<FeedListViewModel>()

    // UI stuff
    private lateinit var rv: RecyclerView
    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var ad: FeedListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val x =  inflater.inflate(R.layout.fragment_feed_list, container, false)
        setupRecyclerView(x)
        subscribeUi()
        return x
    }


    private fun setupRecyclerView(x: View) {
        lm = LinearLayoutManager(this.context)
        ad = FeedListAdapter { clickedRssSource ->
            (activity as NavigationActivity).showFeedEntryFragment(clickedRssSource)
        }
        rv = x.findViewById<RecyclerView>(R.id.rv_feed_sources)
        rv.apply {
            adapter = ad
            layoutManager = lm
        }
    }

    private fun subscribeUi() {
        feedListViewModel.rssSources.observe(this, Observer { list ->
            renderList(list)
        })
    }

    private fun renderList(list: List<RssFeedSource>) {
        if (list.isNotEmpty()) ad.updateData(list)

    }

    override fun onResume() {
        super.onResume()
        feedListViewModel.getSources()
    }


}
