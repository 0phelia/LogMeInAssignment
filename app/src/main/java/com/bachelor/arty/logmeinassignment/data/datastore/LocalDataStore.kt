package com.bachelor.arty.logmeinassignment.data.datastore

import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource

class LocalDataStore constructor(
    // ideally we would have some DB here but since it's a dummy project we make use of in-memory collections
) : DataStore {
    override suspend fun isEmpty(source: RssFeedSource): Boolean {
        return cachedMap[source] == null
    }

    var cachedMap: MutableMap<RssFeedSource, List<ArticleEntity>> = mutableMapOf()

    override suspend fun saveFeed(source: RssFeedSource, result: List<ArticleEntity>) {
        cachedMap[source] = result
    }

    override suspend fun getRssFeed(source: RssFeedSource): List<ArticleEntity> {
        return cachedMap[source] ?: listOf()
    }

    override suspend fun clearAll() {
        cachedMap.clear()
    }

}