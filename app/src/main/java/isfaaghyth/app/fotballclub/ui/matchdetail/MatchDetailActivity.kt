package isfaaghyth.app.fotballclub.ui.matchdetail

import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.local.entities.MatchEntity
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.utils.DelimeterUtil
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchDetailActivity : BaseActivity<MatchDetailPresenter>(), MatchDetailView {

    override fun presenter(): MatchDetailPresenter = MatchDetailPresenter(this)
    override fun contentView(): Int = R.layout.activity_match_detail

    private lateinit var match: Match
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreated() {
        match = intent.getSerializableExtra("match") as Match
        favoriteState()
        showBackButton(true)
        matchDetail(match)
    }

    private fun favoriteState() = database.use {
        val result = select(MatchEntity.TABLE_MATCH)
                .whereArgs("(EVENT_ID = {id})", "id" to match.idEvent)
        val favorite = result.parseList(classParser<MatchEntity>())
        if (!favorite.isEmpty()) isFavorite = true
        setFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mnFavorite -> {
                isFavorite = if (!isFavorite) {
                    presenter().addToFavorite(this, match)
                    !isFavorite
                } else {
                    presenter().removeFromFavorite(this, match.idEvent)
                    !isFavorite
                }
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun matchDetail(match: Match) {
        presenter().getTeamsBadge(match.idHomeTeam, match.idAwayTeam)
        txtDate.text = match.dateEvent

        //home
        txtHomeTeam.text = match.strHomeTeam
        txtHomeScorer.text = DelimeterUtil.comma(match.strHomeGoalDetails)
        txtHomeScore.text = match.intHomeScore

        txtGoalHomeKeeper.text = match.strHomeLineupGoalkeeper
        txtHomeDefense.text = DelimeterUtil.comma(match.strHomeLineupDefense)
        txtHomeMidfield.text = DelimeterUtil.comma(match.strHomeLineupMidfield)
        txtHomeForward.text = DelimeterUtil.comma(match.strHomeLineupForward)
        txtHomeSubtitles.text = DelimeterUtil.comma(match.strHomeLineupSubstitutes)

        //away
        txtAwayTeam.text = match.strAwayTeam
        txtAwayScorer.text = DelimeterUtil.comma(match.strAwayGoalDetails)
        txtAwayScore.text = match.intAwayScore

        txtGoalAwayKeeper.text = match.strAwayLineupGoalkeeper
        txtAwayDefense.text = DelimeterUtil.comma(match.strAwayLineupDefense)
        txtAwayMidfield.text = DelimeterUtil.comma(match.strAwayLineupMidfield)
        txtAwayForward.text = DelimeterUtil.comma(match.strAwayLineupForward)
        txtAwaySubtitles.text = DelimeterUtil.comma(match.strAwayLineupSubstitutes)
    }

    override fun getTeamsBadge(homeBadge: String, awayBadge: String) {
        Glide.with(applicationContext)
                .load(homeBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                .into(imgHomeTeam)

        Glide.with(applicationContext)
                .load(awayBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                .into(imgAwayTeam)
    }

    private fun setFavorite() = if (isFavorite) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.mipmap.ic_favorite)
    } else {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.mipmap.ic_unfavorite)
    }
}
