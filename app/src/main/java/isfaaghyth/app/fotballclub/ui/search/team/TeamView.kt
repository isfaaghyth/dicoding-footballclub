package isfaaghyth.app.fotballclub.ui.search.team

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.Teams

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
interface TeamView : BaseView {
    fun onTeamsData(team: Teams)
}