package com.bachelor.arty.logmeinassignment.data.repository

import com.bachelor.arty.logmeinassignment.common.Result
import com.bachelor.arty.logmeinassignment.data.datastore.LocalDataStore
import com.bachelor.arty.logmeinassignment.data.datastore.RemoteDataStore
import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource

class MainRepositoryImpl constructor(
    private val localDataStore: LocalDataStore,
    private	val remoteDataStore: RemoteDataStore
): MainRepository {

    override fun getRssFeedSources(): Result<List<RssFeedSource>> {
        return Result.Success(listOf(
            RssFeedSource.AlJazeera(),
            RssFeedSource.FeedSpot(),
            RssFeedSource.Independent(),
            RssFeedSource.Yahoo()
        ))
    }

    override suspend fun getArticlesForSource(source: RssFeedSource, descending: Boolean): Result<List<ArticleEntity>> {
        return try {
            // choose the source of data (remote/local)
            val result = if (localDataStore.isEmpty(source)) {
                getDataFromRemoteAndUpdateCache(source)
            } else {
                getDataFromCache(source)
            }
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

    /**
     * Private methods
     */
    private suspend fun getDataFromRemoteAndUpdateCache(source: RssFeedSource): List<ArticleEntity> {
        val result = remoteDataStore.getRssFeed(source)
        localDataStore.saveFeed(source, result)
        return result
    }

    private suspend fun getDataFromCache(source: RssFeedSource) = localDataStore.getRssFeed(source)
}