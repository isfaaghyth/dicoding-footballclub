package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.Leagues
import isfaaghyth.app.fotballclub.data.model.Teams

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
interface TeamsView : BaseView {
    fun onTeamsData(team: Teams)
    fun onAllLeagues(leagues: Leagues)
}