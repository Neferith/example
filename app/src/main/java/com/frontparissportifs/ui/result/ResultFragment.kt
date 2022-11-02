package com.frontparissportifs.ui.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.frontparissportifs.R
import com.frontparissportifs.model.Team
import com.frontparissportifs.ui.detail.DetailActivity
import com.frontparissportifs.utils.DataState
import com.frontparissportifs.utils.displayError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ResultFragment : Fragment(), ResultContract.View {

    @Inject
    lateinit var presenter: ResultContract.Presenter

    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        requireActivity()
            .supportFragmentManager
            .setFragmentResultListener(
                "requestKeyword",
                this
            ) { _, bundle ->
                presenter.onUserSelectKeyword(bundle.getString("bundleKeyword").toString())
            }

        swipeContainer.isEnabled = false
    }

    companion object {
        fun newInstance() = ResultFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attach(this)
        subscribeObservers()
    }

    override fun onDetach() {
        unsubscribeObservers()
        presenter.detach()
        super.onDetach()
    }

    private fun subscribeObservers() {
        presenter.dataState.observe(this) { response ->
            response?.let { processResponse(response) }
        }
    }

    private fun unsubscribeObservers() {
        presenter.dataState.removeObservers(this)
    }

    private fun setupRecyclerView() {
        adapter = TeamAdapter(presenter)
        team_recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        team_recyclerview.adapter = adapter
    }

    private fun processResponse(response: DataState<List<Team>>) {
        when (response) {
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
                displayError(requireContext(), response.exception.message)
            }
        }
    }

    private fun populateRecyclerView(teams: List<Team>) {
        adapter.setItems(ArrayList(teams))
    }

    private fun displayLoading(isLoading: Boolean) {
        swipeContainer.isRefreshing = isLoading
    }

    override fun goToDetail(team: Team) {
        val i = Intent(requireActivity(), DetailActivity::class.java)
        i.putExtra("team", team)
        startActivity(i)
    }

}