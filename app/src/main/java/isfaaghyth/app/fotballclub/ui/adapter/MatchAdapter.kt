package isfaaghyth.app.fotballclub.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.ui.matchdetail.MatchDetailActivity
import isfaaghyth.app.fotballclub.utils.ReminderUtil
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
            parent?.context?.startActivity<MatchDetailActivity>("match" to matches[position])
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
        private val btnAddReminder = view?.btnAddReminder

        fun setMatchData(match: Match) {
            txtHomeTeam?.text = match.strHomeTeam
            txtAwayTeam?.text = match.strAwayTeam
            txtDate?.text = match.dateEvent

            if (match.intHomeScore !== null) {
                val homeScore: Int? = match.intHomeScore?.toInt() ?: 0
                val awayScore: Int? = match.intAwayScore?.toInt() ?: 0
                txtHomeTeam?.text = "${match.strHomeTeam} ($homeScore)"
                txtAwayTeam?.text = "${match.strAwayTeam} ($awayScore)"
                btnAddReminder?.setOnClickListener {
                    ReminderUtil.addEventToGoogleCalendar(itemView.context, match)
                }
            } else {
                btnAddReminder?.visibility = View.GONE
            }
        }
    }

}