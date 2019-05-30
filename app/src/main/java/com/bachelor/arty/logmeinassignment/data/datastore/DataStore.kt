package com.bachelor.arty.logmeinassignment.data.datastore

import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource

interface DataStore {

    suspend fun getRssFeed(source: RssFeedSource): List<ArticleEntity>

    suspend fun clearAll()

    suspend fun isEmpty(source: RssFeedSource): Boolean

    suspend fun saveFeed(source: RssFeedSource, result: List<ArticleEntity>)
}
