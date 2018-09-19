package isfaaghyth.app.fotballclub.ui.matchdetail

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.utils.DelimeterUtil
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : BaseActivity<MatchDetailPresenter>(), MatchDetailView {

    override fun presenter(): MatchDetailPresenter = MatchDetailPresenter(this)
    override fun contentView(): Int = R.layout.activity_match_detail

    private lateinit var match: Match

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
        txtHomeScorer.text = DelimeterUtil.comma(match.strHomeGoalDetails)
        txtHomeScore.text = match.intHomeScore

        txtGoalHomeKeeper.text = match.strHomeLineupGoalkeeper
        txtHomeDefense.text = DelimeterUtil.comma(match.strHomeLineupDefense)
        txtHomeMidfield.text = DelimeterUtil.comma(match.strHomeLineupMidfield)
        txtHomeForward.text = DelimeterUtil.comma(match.strHomeLineupForward)
        txtHomeSubtitles.text = DelimeterUtil.comma(match.strHomeLineupSubstitutes)

        //away
        //imgAwayTeam
        txtAwayTeam.text = match.strAwayTeam
        txtAwayScorer.text = DelimeterUtil.comma(match.strAwayGoalDetails)
        txtAwayScore.text = match.intAwayScore

        txtGoalAwayKeeper.text = match.strAwayLineupGoalkeeper
        txtAwayDefense.text = DelimeterUtil.comma(match.strAwayLineupDefense)
        txtAwayMidfield.text = DelimeterUtil.comma(match.strAwayLineupMidfield)
        txtAwayForward.text = DelimeterUtil.comma(match.strAwayLineupForward)
        txtAwaySubtitles.text = DelimeterUtil.comma(match.strAwayLineupSubstitutes)
    }

}
