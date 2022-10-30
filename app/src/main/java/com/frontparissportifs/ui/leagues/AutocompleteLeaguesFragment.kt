package com.frontparissportifs.ui.leagues

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import com.frontparissportifs.R
import com.frontparissportifs.model.League
import com.frontparissportifs.utils.DataState
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [AutocompleteLeaguesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AutocompleteLeaguesFragment : Fragment(), IAutocompleteLeaguesContract.View {

    @Inject
    lateinit var presenter: IAutocompleteLeaguesContract.Presenter

    @Inject
    lateinit var model: IAutocompleteLeaguesContract.Model

    companion object {
        fun newInstance() = AutocompleteLeaguesFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var adapter: ArrayAdapter<String>
    lateinit var autoCompleteTextView: MaterialAutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_autocomplete_leagues, container, false)

        //Creating the instance of ArrayAdapter containing list of fruit names
        //Creating the instance of ArrayAdapter containing list of fruit names
         adapter=
            ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_item, emptyArray())
        //Getting the instance of AutoCompleteTextView
        //Getting the instance of AutoCompleteTextView

        val atv = view.findViewById<MaterialAutoCompleteTextView>(R.id.autoCompleteTextView)
        autoCompleteTextView = atv

        atv.threshold = 1 //will start working from first character

        atv.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView

        atv.setTextColor(Color.RED)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attach(this,model)
        subscribeObservers()
    }

    override fun onDetach() {
        presenter.detach()
        super.onDetach()
    }

    private fun subscribeObservers() {

        presenter.dataState.observe(this, { response -> response?.let { processResponse(response) } })

    }

    private fun processResponse(response: DataState<List<String>>) {

        when(response) {
            is DataState.Success<List<String>> -> {


                val leagues = arrayOf(response.data)

                adapter=
                    ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_item, response.data.toTypedArray())
                autoCompleteTextView.setAdapter(adapter)

            }
            is DataState.Loading -> {
                //displayLoading(true)
            }
            is DataState.Error -> {
                //displayLoading(false)
                //displayError(response.exception.message)
            }
        }

    }



    lateinit var mCallback: TextClickedListener

    //defining Interface
    interface TextClickedListener {
        fun sendText(text: String)
    }

    fun setOnTextClickedListener(callback: TextClickedListener) {
        this.mCallback = callback
    }

    // TODO: Rename and change types and number of parameters
    fun yourMethodofSendingText() {
        //here you can get the text from the edit text or can use this method according to your need
        mCallback.sendText("YOUR TEXT")
    }

    override fun getSearchValue(): String {
        TODO("Not yet implemented")
    }
}