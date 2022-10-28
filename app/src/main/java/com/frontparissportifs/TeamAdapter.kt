package com.frontparissportifs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.frontparissportifs.model.Team
import kotlinx.android.synthetic.main.item_team.view.*
import javax.inject.Inject

class TeamAdapter(private val listener: TeamItemListener) : RecyclerView.Adapter<TeamViewHolder>() {

    interface TeamItemListener {
        fun onClickedBlog(blogTitle: CharSequence)
    }

    private val items = ArrayList<Team>()

    fun setItems(items: ArrayList<Team>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = items[position]
        holder.textTitle.text = team.name
        holder.textDescription.text = team.description
    }
}

class TeamViewHolder(itemView: View, private val listener: TeamAdapter.TeamItemListener) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val itemLayout: ConstraintLayout = itemView.team_layout
    val textTitle: TextView = itemView.text_title
    val textDescription: TextView = itemView.text_description
    val image: AppCompatImageView = itemView.image

    init {
        itemLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClickedBlog(textTitle.text)
    }
}