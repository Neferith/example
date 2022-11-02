package com.frontparissportifs.ui.autocomplete

import com.frontparissportifs.utils.DataState
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AutocompleteModelTest {

    companion object {
        private val DATA_STATE_SUCCESS: DataState<Boolean> = DataState.Success(true)
    }

    private val model: AutocompleteContract.Model = mockk()
    private val view: AutocompleteContract.View = mockk()

    private val presenter: AutocompleteModelPresenter = AutocompleteModelPresenter(model)

    @Before
    fun setUp() {
        justRun { model.allSoccerLeagues(presenter) }
        justRun { view.executeCloseKeyboard() }
        justRun { view.updateCurrentKeywordSelected("French Ligue 1") }
        every { model.fetchLeagueExist("French Ligue 1", any()) }
            .answers {
            secondArg<AutocompleteContract.Model
            .OnFinishedListener<Boolean>>().onFinished(DATA_STATE_SUCCESS)
        }
        presenter.attach(view)
        verify(exactly = 1) { model.allSoccerLeagues(presenter) }
    }

    @Test
    fun userChooseLeagueKeywordInAutocompleteList() = runTest {
        presenter.onChooseItemInAutocompleteList("French Ligue 1")
        verify(exactly = 1) { view.updateCurrentKeywordSelected("French Ligue 1") }
    }

    @Test
    fun userPerformSearch() = runTest {
        presenter.performSearch("French Ligue 1")
        verify(exactly = 1) { view.executeCloseKeyboard() }
        verify(exactly = 1) { model.fetchLeagueExist("French Ligue 1", any()) }
        verify(exactly = 1) { view.updateCurrentKeywordSelected("French Ligue 1") }
    }
}