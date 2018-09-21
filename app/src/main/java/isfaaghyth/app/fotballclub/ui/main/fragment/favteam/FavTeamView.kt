package isfaaghyth.app.fotballclub.ui.main.fragment.favteam

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.Teams

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
interface FavTeamView : BaseView {
    fun getTeam(teams: Teams)
}