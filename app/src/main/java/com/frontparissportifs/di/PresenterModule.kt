package com.frontparissportifs.di

import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesContract
import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesModel
import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesModelPresenter
import com.frontparissportifs.ui.detail.DetailContract
import com.frontparissportifs.ui.detail.DetailModel
import com.frontparissportifs.ui.detail.DetailPresenter
import com.frontparissportifs.ui.result.ResultContract
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
            AutocompleteLeaguesContract.Presenter

    @Binds
    abstract fun provideAutocompleteModel(impl: AutocompleteLeaguesModel):
            AutocompleteLeaguesContract.Model

    @Binds
    abstract fun provideResultPresenter(impl: ResultPresenter):
            ResultContract.Presenter

    @Binds
    abstract fun provideResultModel(impl: ResultModel):
            ResultContract.Model

    @Binds
    abstract fun provideDetailPresenter(impl: DetailPresenter):
            DetailContract.Presenter

    @Binds
    abstract fun provideDetailModel(impl: DetailModel):
            DetailContract.Model


}