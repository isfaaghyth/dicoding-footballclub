package isfaaghyth.app.fotballclub.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.model.Team
import isfaaghyth.app.fotballclub.ui.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamAdapter.Holder>() {

    override fun getItemCount(): Int = teams.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val viewHolder = Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_team, parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent?.context?.startActivity<TeamDetailActivity>("team" to teams[position])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.setTeam(teams[position])
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgTeam = view.imgTeam
        private val txtTeamName = view.txtTeamName

        fun setTeam(team: Team) {
            Glide.with(itemView.context)
                    .load(team.strTeamBadge)
                    .into(imgTeam)
            txtTeamName.text = team.strTeam
        }
    }

}