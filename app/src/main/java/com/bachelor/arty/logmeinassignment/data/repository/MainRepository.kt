package com.bachelor.arty.logmeinassignment.data.repository

import com.bachelor.arty.logmeinassignment.common.Result
import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource

interface MainRepository {

    fun getRssFeedSources(): Result<List<RssFeedSource>>

    suspend fun getArticlesForSource(source: RssFeedSource, descending: Boolean): Result<List<ArticleEntity>>

}