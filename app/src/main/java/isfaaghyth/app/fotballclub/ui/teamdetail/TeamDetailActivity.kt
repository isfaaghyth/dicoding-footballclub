package isfaaghyth.app.fotballclub.ui.teamdetail

import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import com.bumptech.glide.Glide
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.local.entities.TeamEntity
import isfaaghyth.app.fotballclub.data.model.Players
import isfaaghyth.app.fotballclub.data.model.Team
import isfaaghyth.app.fotballclub.ui.adapter.PlayerAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 * @TODO(still unused code, but maybe will be use it for the next submission)
 */
class TeamDetailActivity : BaseActivity<TeamDetailPresenter>(), TeamDetailView {

    override fun presenter(): TeamDetailPresenter = TeamDetailPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.activity_team_detail

    private lateinit var team: Team
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreated() {
        showBackButton(true)
        team = intent.getSerializableExtra("team") as Team
        lstPlayers.layoutManager = GridLayoutManager(context(), 3)
        showTeamDetail(team)
    }


    private fun favoriteState() = database.use {
        val result = select(TeamEntity.TABLE_TEAM).whereArgs("(TEAM_ID = {id})", "id" to team.idTeam)
        val favorite = result.parseList(classParser<TeamEntity>())
        if (!favorite.isEmpty()) isFavorite = true
        setFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        favoriteState()
        return super.onCreateOptionsMenu(menu)
    }

    private fun showTeamDetail(team: Team) {
        Glide.with(this)
                .load(team.strTeamBadge)
                .into(imgTeam)
        txtTeamName.text = team.strTeam
        txtOverview.text = team.strDescriptionEN
        presenter().getPlayerByTeam(team.idTeam)
    }

    private fun setFavorite() = if (isFavorite) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.mipmap.ic_favorite)
    } else {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.mipmap.ic_unfavorite)
    }

    override fun onAllPlayers(players: Players) {
        lstPlayers.adapter = PlayerAdapter(players.player)
    }

}