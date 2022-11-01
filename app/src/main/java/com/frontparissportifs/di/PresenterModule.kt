package com.frontparissportifs.di

import com.frontparissportifs.ui.autocomplete.AutocompleteContract
import com.frontparissportifs.ui.autocomplete.AutocompleteModel
import com.frontparissportifs.ui.autocomplete.AutocompleteModelPresenter
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
    abstract fun provideAutocompletePresenter(impl: AutocompleteModelPresenter):
            AutocompleteContract.Presenter

    @Binds
    abstract fun provideAutocompleteModel(impl: AutocompleteModel):
            AutocompleteContract.Model

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