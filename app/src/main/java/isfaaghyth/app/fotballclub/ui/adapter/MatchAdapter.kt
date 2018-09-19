package isfaaghyth.app.fotballclub.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.model.Match
import kotlinx.android.synthetic.main.item_match_schedule.view.*

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class MatchAdapter(private var matches: List<Match>): RecyclerView.Adapter<MatchAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val viewHolder = Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_match_schedule, parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            Log.e("TAG", matches[position].strHomeTeam)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.setMatchData(matches[position])
    }

    override fun getItemCount(): Int = matches.size

    inner class Holder(view: View?): RecyclerView.ViewHolder(view) {
        private val txtHomeTeam = view?.txtHomeTeam
        private val txtAwayTeam = view?.txtAwayTeam
        private val txtDate = view?.txtDate

        fun setMatchData(match: Match) {
            txtHomeTeam?.text = match.strHomeTeam
            txtAwayTeam?.text = match.strAwayTeam
            txtDate?.text = match.dateEvent
        }
    }

}