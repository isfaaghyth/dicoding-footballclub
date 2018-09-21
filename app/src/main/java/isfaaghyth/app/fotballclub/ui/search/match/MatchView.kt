package isfaaghyth.app.fotballclub.ui.search.match

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.Events

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
interface MatchView : BaseView {
    fun onMatchData(matches: Events)
}