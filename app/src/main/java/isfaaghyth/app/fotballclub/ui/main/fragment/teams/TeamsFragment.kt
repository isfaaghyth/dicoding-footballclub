package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import android.util.Log
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.TeamRepository
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamsFragment : BaseFragment<TeamsPresenter>(), TeamsView {

    override fun presenter(): TeamsPresenter = TeamsPresenter(this, AppSchedulerProvider())

    override fun contentView(): Int = R.layout.fragment_teams_detail

    override fun onCreated() {
        presenter().getTeamByLeague("English Premier League")
    }

    override fun onTeamsData(team: TeamRepository) {
        for (s in team.teams) {
            Log.d("TAG", s.strTeam)
        }
    }

}