package isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.MatchEvent

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
interface NextMatchView : BaseView {
    fun onNextMatchData(matches: MatchEvent)
}