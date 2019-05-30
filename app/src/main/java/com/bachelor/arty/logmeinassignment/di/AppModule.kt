package com.bachelor.arty.logmeinassignment.di

import com.bachelor.arty.logmeinassignment.data.datastore.DataStore
import com.bachelor.arty.logmeinassignment.data.datastore.LocalDataStore
import com.bachelor.arty.logmeinassignment.data.datastore.RemoteDataStore
import com.bachelor.arty.logmeinassignment.data.datastore.RssResponseToEntityMapper
import com.bachelor.arty.logmeinassignment.data.repository.MainRepository
import com.bachelor.arty.logmeinassignment.data.repository.MainRepositoryImpl
import com.bachelor.arty.logmeinassignment.presentation.feed_entry.FeedEntryViewModel
import com.bachelor.arty.logmeinassignment.presentation.feed_list.FeedListViewModel
import com.prof.rssparser.Parser
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModule {

    fun getModule(): Module = module {

        // repos
        single<MainRepository> { MainRepositoryImpl( get(named("localDS")),get(named("remoteDS"))) }

        // data stores
        single<DataStore> (named("localDS")){ LocalDataStore() }
        single<DataStore> (named("remoteDS")) { RemoteDataStore( get(), get() ) }

        single<Parser> { Parser() }
        single<RssResponseToEntityMapper> { RssResponseToEntityMapper() }


        // browse feature
        viewModel { FeedListViewModel(get()) }
        viewModel { FeedEntryViewModel(get()) }
    }

}