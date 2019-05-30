package com.bachelor.arty.logmeinassignment.data.datastore

import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import com.prof.rssparser.Parser

class RemoteDataStore constructor(
    private val rssParser: Parser,
    private val mapper: RssResponseToEntityMapper
): DataStore {
    override suspend fun saveFeed(source: RssFeedSource, result: List<ArticleEntity>) {
        throw UnsupportedOperationException("Operation not supported")
    }

    override suspend fun getRssFeed(source: RssFeedSource): List<ArticleEntity> {
        val response =  rssParser.getArticles(source.source)
        return mapper.map(response)
    }

    override suspend fun clearAll() {
        throw UnsupportedOperationException("Operation not supported")
    }

    override suspend fun isEmpty(rssFeedSource: RssFeedSource): Boolean {
        throw UnsupportedOperationException("Operation not supported")
    }


}