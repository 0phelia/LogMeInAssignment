package com.bachelor.arty.logmeinassignment.presentation.feed_entry

import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity

data class FeedEntriesViewState(
    val loading: Boolean = true,
    val errorMessage: String? = null,
    val entries: List<ArticleEntity> = emptyList()
)