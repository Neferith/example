package com.frontparissportifs.ui.autocomplete

import com.frontparissportifs.utils.DataState
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AutocompleteModelTest {

    private val model: AutocompleteContract.Model = mockk()
    private val view: AutocompleteContract.View = mockk()


    private val dataState: DataState<Boolean> = mockk()

    //  private val listener: AutocompleteContract.Model.OnFinishedListener<List<String>> = mockk()

    private val presenter: AutocompleteModelPresenter = AutocompleteModelPresenter(model)

    @Before
    fun setUp() {
        justRun { model.allSoccerLeagues(presenter) }
        justRun { view.executeCloseKeyboard() }
        justRun { view.updateCurrentKeywordSelected("French Ligue 1") }
        every { model.fetchLeagueExist("French Ligue 1", any()) }.answers {
            secondArg<AutocompleteContract.Model.OnFinishedListener<Boolean>>().onFinished(dataState)
        }
        presenter.attach(view)
        //every { view.updateCurrentKeywordSelected("French Ligue 1") }.
    }

    @Test
    fun userChooseLeagueKeywordInAutocompleteList() = runTest {
        presenter.onChooseItemInAutocompleteList("French Ligue 1")
        verify(exactly = 1) { model.allSoccerLeagues(presenter) }
        verify { view.updateCurrentKeywordSelected("French Ligue 1") }
    }

    @Test
    fun userPerformSearch() = runTest {
        presenter.performSearch("French Ligue 1")
        verify(exactly = 1) { view.executeCloseKeyboard() }
        verify(exactly = 1) { model.allSoccerLeagues(presenter) }
        verify { view.updateCurrentKeywordSelected("French Ligue 1") }
    }
}