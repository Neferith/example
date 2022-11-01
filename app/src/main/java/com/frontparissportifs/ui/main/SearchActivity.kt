package com.frontparissportifs.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frontparissportifs.R
import com.frontparissportifs.ui.autocomplete.AutocompleteFragment
import com.frontparissportifs.ui.result.ResultFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AutocompleteFragment.newInstance())
                .commitNow()
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerResult, ResultFragment.newInstance())
                .commitNow()
        }
    }
}