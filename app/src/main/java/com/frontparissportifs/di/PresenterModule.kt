package com.frontparissportifs.di

import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.features.search.SearchModel
import com.frontparissportifs.features.search.SearchPresenter
import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesModel
import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesModelPresenter
import com.frontparissportifs.ui.autocomplete.IAutocompleteLeaguesContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PresenterModule {
    @Binds
    abstract fun provideSearchPresenter(impl: SearchPresenter): ISearchContract.Presenter

    @Binds
    abstract fun provideSearchModel(impl: SearchModel): ISearchContract.Model


    @Binds
    abstract fun provideAutocompletePresenter(impl: AutocompleteLeaguesModelPresenter): IAutocompleteLeaguesContract.Presenter

    @Binds
    abstract fun provideAutocompleteModel(impl: AutocompleteLeaguesModel): IAutocompleteLeaguesContract.Model
}