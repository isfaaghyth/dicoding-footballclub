package isfaaghyth.app.fotballclub.ui.matchdetail

import android.util.Log

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.model.Match
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : BaseActivity() {

    private lateinit var match: Match

    override fun contentView(): Int = R.layout.activity_match_detail

    override fun onCreated() {
        showBackButton(true)
        match = intent.getSerializableExtra("match") as Match
        matchDetail(match)
    }

    fun matchDetail(match: Match) {
        txtDate.text = match.dateEvent

        //home
        //imgHomeTeam
        txtHomeTeam.text = match.strHomeTeam
        txtHomeScorer.text = match.strHomeGoalDetails
        txtHomeScore.text = match.intHomeScore

        txtGoalHomeKeeper.text = match.strHomeLineupGoalkeeper
        txtHomeDefense.text = match.strHomeLineupDefense
        txtHomeMidfield.text = match.strHomeLineupMidfield
        txtHomeForward.text = match.strHomeLineupForward
        txtHomeSubtitles.text = match.strHomeLineupSubstitutes

        //away
        txtAwayTeam.text = match.strAwayTeam
        txtAwayScorer.text = match.strAwayGoalDetails
        txtAwayScore.text = match.intAwayScore

        txtGoalAwayKeeper.text = match.strAwayLineupGoalkeeper
        txtAwayDefense.text = match.strAwayLineupDefense
        txtAwayMidfield.text = match.strAwayLineupMidfield
        txtAwayForward.text = match.strAwayLineupForward
        txtAwaySubtitles.text = match.strAwayLineupSubstitutes
    }

}
