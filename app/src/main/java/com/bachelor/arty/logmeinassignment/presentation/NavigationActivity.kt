package com.bachelor.arty.logmeinassignment.presentation

import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource

interface NavigationActivity {
    fun openBrowserWithUrl(url: String)

    fun showFeedEntryFragment(source: RssFeedSource)
}
