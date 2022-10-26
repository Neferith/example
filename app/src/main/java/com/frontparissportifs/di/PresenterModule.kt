package com.frontparissportifs.di

import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.features.search.SearchPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PresenterModule {

    @Binds
    abstract fun provideSearchPresenter(impl: SearchPresenter): ISearchContract.Presenter
}