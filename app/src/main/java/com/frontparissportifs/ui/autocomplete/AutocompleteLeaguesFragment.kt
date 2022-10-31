package com.frontparissportifs.ui.autocomplete

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.frontparissportifs.R
import com.frontparissportifs.utils.DataState
import com.frontparissportifs.utils.displayError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_autocomplete_leagues.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [AutocompleteLeaguesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AutocompleteLeaguesFragment : Fragment(), AutocompleteLeaguesContract.View {

    @Inject
    lateinit var presenter: AutocompleteLeaguesContract.Presenter

    @Inject
    lateinit var model: AutocompleteLeaguesContract.Model

    companion object {
        fun newInstance() = AutocompleteLeaguesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_autocomplete_leagues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAutoCompleteTextView()
    }

    private fun setupAutoCompleteTextView() {
        val adapter =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.select_dialog_item,
                emptyArray()
            )
        autoCompleteTextView.threshold = 1
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setTextColor(Color.RED)
        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            presenter.onChooseItemInAutocompleteList(parent.adapter.getItem(position) as String)
        }
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
        presenter.dataState.observe(
            this
        ) { response -> response?.let { processResponse(response) } }
    }

    private fun unsubscribeObservers() {
        presenter.dataState.removeObservers(this)
    }

    private fun processResponse(response: DataState<List<String>>) {
        when (response) {
            is DataState.Success<List<String>> -> {
                val adapter =
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.select_dialog_item,
                        response.data.toTypedArray()
                    )
                autoCompleteTextView.setAdapter(adapter)
            }
            is DataState.Loading -> {
                //displayLoading(true)
            }
            is DataState.Error -> {
                //displayLoading(false)
                displayError(requireContext(), response.exception.message)
            }
        }
    }

    override fun updateCurrentKeywordSelected(keyword: String) {
        requireActivity()
            .supportFragmentManager
            .setFragmentResult(
                "requestKeyword",
                bundleOf("bundleKeyword" to keyword)
            )
    }

}