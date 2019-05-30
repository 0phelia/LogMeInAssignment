package com.bachelor.arty.logmeinassignment.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bachelor.arty.logmeinassignment.R
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import com.bachelor.arty.logmeinassignment.presentation.feed_entry.FeedEntriesFragment
import com.bachelor.arty.logmeinassignment.presentation.feed_list.FeedListFragment

    const val SELECTED_SOURCE: String = "SELECTED_SOURCE"

class MainActivity : AppCompatActivity(), NavigationActivity {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView(savedInstanceState)
    }

    private fun setupView(savedInstanceState: Bundle?) {
        if (findViewById<View>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) return
            showFeedListFragment()
        }
    }

    fun showFeedListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FeedListFragment().apply { arguments = intent.extras })
            .commit()
    }

    override fun openBrowserWithUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun showFeedEntryFragment(source: RssFeedSource) {

        val detailArg = Bundle().apply {
            putInt(SELECTED_SOURCE, source.id)
        }

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, FeedEntriesFragment().apply { arguments = detailArg})
            .commit()
    }

}
