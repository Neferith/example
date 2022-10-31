package com.frontparissportifs.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frontparissportifs.R
import com.frontparissportifs.model.Team
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), IDetailContract.View {

    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter.attach(this)
        subscribeObservers()

        val b = intent.extras
        val team = b?.getParcelable("team") as Team?
        if (team != null) {
            presenter.selectTeam(team)
        }
    }

    override fun onDestroy() {
        unsubscribeObservers()
        presenter.detach()
        super.onDestroy()
    }

    private fun subscribeObservers() {
        presenter.dataState.observe(
            this
        ) { response -> response?.let { processResponse(response) } }
    }

    private fun unsubscribeObservers() {
        presenter.dataState.removeObservers(this)
    }

    private fun processResponse(response: Team) {
        team_title.text = response.name
    }

}