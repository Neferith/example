package com.frontparissportifs

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.features.search.SearchModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), ISearchContract.View {

    @Inject
    lateinit var presenter: ISearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        var search = findViewById<TextInputLayout>(R.id.search)
        search.editText?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                presenter?.onSearchClick()
                // your code here
                true
            }
            false
        })

        presenter?.attach(this, SearchModel())
    }


    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }


    override fun getSearchValue(): String {
        return "English Premier League"
    }
}