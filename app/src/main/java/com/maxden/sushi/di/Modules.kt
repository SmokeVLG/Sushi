package com.maxden.sushi.di

import com.maxden.sushi.database.getDatabase
import com.maxden.sushi.repository.HomeRepository

import com.maxden.sushi.viewmodels.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(getDatabase(androidApplication())) }
}

val viewModelModule = module(override = true) {
    viewModel {
        HomeViewModel(get())
    }

}