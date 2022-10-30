package com.frontparissportifs

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.model.Team
import com.frontparissportifs.ui.autocomplete.AutocompleteLeaguesFragment
import com.frontparissportifs.utils.DataState
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), ISearchContract.View, TeamAdapter.TeamItemListener {

    @Inject
    lateinit var presenter: ISearchContract.Presenter

    @Inject
    lateinit var model: ISearchContract.Model

    lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AutocompleteLeaguesFragment.newInstance())
                .commitNow()
        }

        supportFragmentManager.setFragmentResultListener("requestKeyword",this) { requestKey, bundle ->
            testsearchValue = bundle.getString("bundleKeyword").toString()
            presenter.onSearchClick()
        }

        setupRecyclerView()

        var search = findViewById<TextInputLayout>(R.id.search)
        search.editText?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                presenter?.onSearchClick()
                // your code here
                true
            }
            false
        })

        subscribeObservers()
        presenter?.attach(this, model)
    }





    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    private fun subscribeObservers() {

        model.dataState.observe(this, Observer { response -> response?.let { processResponse(response) } })

    }

    private fun setupRecyclerView() {
        adapter = TeamAdapter(this)
        team_recyclerview.layoutManager = LinearLayoutManager(this)
        team_recyclerview.adapter = adapter
    }


    private fun processResponse(response: DataState<List<Team>>) {

        when(response) {
            is DataState.Success<List<Team>> -> {
                displayLoading(false)
                populateRecyclerView(response.data)
            }
            is DataState.Loading -> {
                displayLoading(true)
                populateRecyclerView(ArrayList())
            }
            is DataState.Error -> {
                displayLoading(false)
                displayError(response.exception.message)
            }
        }

    }

    private fun populateRecyclerView(teams: List<Team>) {
        adapter.setItems(ArrayList(teams))
    }

    private fun displayError(message: String?) {
        if (message.isNullOrEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        //swipeRefreshLayout.isRefreshing = isLoading
    }



    var testsearchValue = ""
    override fun getSearchValue(): String {
        return testsearchValue
    }

    override fun onClickedTeam(teamTitle: CharSequence) {
        TODO("Not yet implemented")
    }
}