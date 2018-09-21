package isfaaghyth.app.fotballclub.ui.search.team

import android.support.v7.widget.GridLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.model.Teams
import isfaaghyth.app.fotballclub.ui.adapter.TeamAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.activity_search_team.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamActivity : BaseActivity<TeamPresenter>(), TeamView {

    override fun presenter(): TeamPresenter = TeamPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.activity_search_team

    override fun onCreated() {
        val querySearch = intent.getStringExtra("search")
        lstTeams.layoutManager = GridLayoutManager(this, 3)
        presenter().searchTeamByName(querySearch)
    }

    override fun onTeamsData(team: Teams) = if (team.teams.isNotEmpty()) {
        lstTeams.adapter = TeamAdapter(team.teams)
    } else {
        onInfo(getString(R.string.not_found))
    }

}