package com.frontparissportifs

import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.features.search.SearchPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchPresenterUnitTest {

    @Mock
    private lateinit var view: ISearchContract.View

    @Mock
    private lateinit var model: ISearchContract.Model

    @Spy
    private var presenter: SearchPresenter = SearchPresenter()

    @Before
    fun setUp() {
        presenter.attach(view, model)
    }

    @Test
    fun test_SearchNotResult() {

        Mockito.`when`(presenter.model?.searchTeams("Test"))
            .then { presenter.view?.onFailure("CODE_ERREUR", "") }

        Mockito.`when`(view.getSearchValue()).thenReturn("Test")

        presenter.onSearchClick()

        Mockito.verify(view, times(1))
            .onFailure("CODE_ERREUR", "")
    }

    @Test
    fun test_SearchNullSearch() {

        Mockito.`when`(presenter.model?.searchTeams("Test"))
            .then { presenter.view?.onFailure("CODE_ERREUR", "") }

        Mockito.`when`(view.getSearchValue()).thenReturn(null)

        presenter.onSearchClick()

        Mockito.verify(view, times(1))
            .onFailure("CODE_ERREUR", "")
    }

    @Test
    fun test_SearchEmptySearch() {

        Mockito.`when`(presenter.model?.searchTeams("Test"))
            .then { presenter.view?.onFailure("CODE_ERREUR", "") }

        Mockito.`when`(view.getSearchValue()).thenReturn("")

        presenter.onSearchClick()

        Mockito.verify(view, times(1))
            .onFailure("CODE_ERREUR", "")
    }

    @Test
    fun test_SearchSuccess() {

        Mockito.`when`(presenter.model?.searchTeams("Test"))
            .then { presenter.view?.onFailure("CODE_ERREUR", "") }

        Mockito.`when`(view.getSearchValue()).thenReturn("")

        presenter.onSearchClick()

        Mockito.verify(view, times(1))
            .onFailure("CODE_ERREUR", "")
    }
}