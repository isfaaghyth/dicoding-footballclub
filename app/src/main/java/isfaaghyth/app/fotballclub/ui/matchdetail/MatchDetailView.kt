package isfaaghyth.app.fotballclub.ui.matchdetail

import isfaaghyth.app.fotballclub.base.BaseView

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
interface MatchDetailView : BaseView {
    fun getTeamsBadge(homeBadge: String, awayBadge: String)
}