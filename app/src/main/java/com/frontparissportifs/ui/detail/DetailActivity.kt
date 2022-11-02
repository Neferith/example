package com.frontparissportifs.ui.detail

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.frontparissportifs.R
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.px
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), DetailContract.View {

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
        team_country.text = response.country
        team_league.text = response.leagues.map { it.name }.joinToString { "${it} " }
        team_description.text = response.description
        val displayMetrics: DisplayMetrics = this.resources.displayMetrics
        Glide.with(team_container).load(response.banner).skipMemoryCache(true).fitCenter()
            .placeholder(com.google.android.material.R.color.material_grey_300)
            .error(com.google.android.material.R.color.material_grey_300)
            .override(displayMetrics.widthPixels.px,250.px)// look here
            .into(team_banner)
    }

}