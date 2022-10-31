package com.frontparissportifs.di

import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesModel
import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesModelPresenter
import com.frontparissportifs.ui.autocomplete.IAutocompleteLeaguesContract
import com.frontparissportifs.ui.detail.DetailModel
import com.frontparissportifs.ui.detail.DetailPresenter
import com.frontparissportifs.ui.detail.IDetailContract
import com.frontparissportifs.ui.result.IResultContract
import com.frontparissportifs.ui.result.ResultModel
import com.frontparissportifs.ui.result.ResultPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PresenterModule {

    @Binds
    abstract fun provideAutocompletePresenter(impl: AutocompleteLeaguesModelPresenter):
            IAutocompleteLeaguesContract.Presenter

    @Binds
    abstract fun provideAutocompleteModel(impl: AutocompleteLeaguesModel):
            IAutocompleteLeaguesContract.Model

    @Binds
    abstract fun provideResultPresenter(impl: ResultPresenter):
            IResultContract.Presenter

    @Binds
    abstract fun provideResultModel(impl: ResultModel):
            IResultContract.Model

    @Binds
    abstract fun provideDetailPresenter(impl: DetailPresenter):
            IDetailContract.Presenter

    @Binds
    abstract fun provideDetailModel(impl: DetailModel):
            IDetailContract.Model


}