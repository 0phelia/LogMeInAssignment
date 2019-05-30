package com.bachelor.arty.logmeinassignment.presentation.feed_entry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bachelor.arty.logmeinassignment.common.Result
import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import com.bachelor.arty.logmeinassignment.data.repository.MainRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FeedEntryViewModel constructor(val mainRepository: MainRepository): ViewModel() {

    val feedEntryViewState: MutableLiveData<FeedEntriesViewState> = MutableLiveData()
    fun getArticles(selectedFeedSource: RssFeedSource) {
        job?.cancel()
        job = GlobalScope.launch {
            val aritvles = mainRepository.getArticlesForSource(selectedFeedSource, true)
            when (aritvles) {
                is Result.Success -> handleSuccess(aritvles.value)
                is Result.Error -> handleError(aritvles.exception)
            }
        }

    }


    init {
        feedEntryViewState.value = FeedEntriesViewState()
    }

    private var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun handleSuccess(t: List<ArticleEntity>) {
        feedEntryViewState.postValue(FeedEntriesViewState(loading = false, entries = t))
    }

    private fun handleError(e: Throwable) {
        feedEntryViewState.postValue(
            feedEntryViewState.value?.copy(
                loading = false,
                errorMessage = e.message ?: "empty error"
            )
        )
    }
}
