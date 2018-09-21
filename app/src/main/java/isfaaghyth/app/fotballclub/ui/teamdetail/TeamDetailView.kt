package isfaaghyth.app.fotballclub.ui.teamdetail

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.Players

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
interface TeamDetailView : BaseView {
    fun onAllPlayers(players: Players)
}