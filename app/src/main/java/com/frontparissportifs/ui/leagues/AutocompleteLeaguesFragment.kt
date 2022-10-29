package com.frontparissportifs.ui.leagues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frontparissportifs.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AutocompleteLeaguesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AutocompleteLeaguesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_autocomplete_leagues, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AutocompleteLeaguesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AutocompleteLeaguesFragment().apply {
                arguments = Bundle().apply {

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
}