package com.frontparissportifs

import com.frontparissportifs.dao.LeagueDao
import com.frontparissportifs.ui.result.ResultContract
import com.frontparissportifs.ui.result.ResultModel
import com.frontparissportifs.ui.result.ResultPresenter
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

open class MockModel{


}

class SearchPresenterUnitTest {

    private var model: ResultContract.Model = mockk()


    private val presenter: ResultPresenter = ResultPresenter(model)

    @Before
    fun setUp() {

       // presenter.attach(view)
    }

    @Test
    fun test_SearchNotResult() {

      /*  Mockito.`when`(presenter.model?.searchTeams("Test"))
            .then {model.dataState }

        Mockito.`when`(view.getSearchValue()).thenReturn("Test")

        presenter.onSearchClick()

        Mockito.verify(view, times(1))
            .onFailure("CODE_ERREUR", "")*/
    }


}