package com.frontparissportifs.ui.result

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frontparissportifs.R
import com.frontparissportifs.model.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(
    private val listener: TeamItemListener
    ) : RecyclerView.Adapter<TeamViewHolder>() {

    interface TeamItemListener {
        fun onClickedTeam(team: Team)
    }

    private val items = ArrayList<Team>()

    // TODO : Revoir le chargement des résultats
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<Team>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = items[position]
        holder.team = team
        Glide.with(holder.itemLayout).load(team.badge)
            .placeholder(com.google.android.material.R.color.material_grey_300)
            .error(com.google.android.material.R.color.material_grey_300)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }
}

class TeamViewHolder(itemView: View, private val listener: TeamAdapter.TeamItemListener) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val itemLayout: ConstraintLayout = itemView.team_layout
    var team: Team? = null
    val image: AppCompatImageView = itemView.image

    init {
        itemLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        team.let { if (it != null) listener.onClickedTeam(it) }
    }
}