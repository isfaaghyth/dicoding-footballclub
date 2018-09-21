package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import android.support.v7.widget.GridLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Teams
import isfaaghyth.app.fotballclub.ui.adapter.TeamAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_teams_detail.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamsFragment : BaseFragment<TeamsPresenter>(), TeamsView {

    override fun presenter(): TeamsPresenter = TeamsPresenter(this, AppSchedulerProvider())

    override fun contentView(): Int = R.layout.fragment_teams_detail

    override fun onCreated() {
        lstTeams.layoutManager = GridLayoutManager(context(), 3)
        presenter().getTeamByLeague("English Premier League")
    }

    override fun onTeamsData(team: Teams) {
        lstTeams.adapter = TeamAdapter(team.teams)
    }

}