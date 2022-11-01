package com.frontparissportifs.ui.autocomplete

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.core.content.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.frontparissportifs.R
import com.frontparissportifs.utils.DataState
import com.frontparissportifs.utils.displayError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_autocomplete.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [AutocompleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AutocompleteFragment : Fragment(), AutocompleteContract.View {

    @Inject
    lateinit var presenter: AutocompleteContract.Presenter

    @Inject
    lateinit var model: AutocompleteContract.Model

    companion object {
        fun newInstance() = AutocompleteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_autocomplete,
            container,
            false)
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
        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            presenter.onChooseItemInAutocompleteList(parent.adapter.getItem(position) as String)
        }
        autoCompleteTextView.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.performSearch(textView.editableText.toString())
                return@setOnEditorActionListener false
            }
            return@setOnEditorActionListener false
        }
    }

    override fun executeCloseKeyboard() {
        autoCompleteTextView.clearFocus();
        val inputMethodManager =
            requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        // on below line hiding our keyboard.
        inputMethodManager.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0)
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
        presenter.allLeaguesNamesState.observe(
            this
        ) { response -> response?.let { processResponse(response) } }
    }

    private fun unsubscribeObservers() {
        presenter.allLeaguesNamesState.removeObservers(this)
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
            is DataState.Loading -> {}
            is DataState.Error -> { }
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