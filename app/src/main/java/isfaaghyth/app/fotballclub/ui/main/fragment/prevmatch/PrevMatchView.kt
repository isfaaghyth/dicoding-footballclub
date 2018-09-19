package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.MatchEvent

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
interface PrevMatchView : BaseView {
    fun onPrevMatchData(matches: MatchEvent)
}