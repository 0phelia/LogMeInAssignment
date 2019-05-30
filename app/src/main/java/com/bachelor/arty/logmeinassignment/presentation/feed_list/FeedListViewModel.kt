package com.bachelor.arty.logmeinassignment.presentation.feed_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bachelor.arty.logmeinassignment.common.Result
import com.bachelor.arty.logmeinassignment.data.entity.RssFeedSource
import com.bachelor.arty.logmeinassignment.data.repository.MainRepository

class FeedListViewModel constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val rssSources: MutableLiveData<List<RssFeedSource>> = MutableLiveData()

    init {
        rssSources.value = listOf()
    }

    fun getSources() {
        val sources = mainRepository.getRssFeedSources()
        when (sources) {
            is Result.Success -> handleSuccess(sources.value)
            is Result.Error -> handleError(sources.exception)
        }

    }

    private fun handleSuccess(list: List<RssFeedSource>) {
        rssSources.postValue(list)
    }

    private fun handleError(e: Throwable) {
        // cannot happen under these circumstances
    }

}