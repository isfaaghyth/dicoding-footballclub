package isfaaghyth.app.fotballclub.ui.main.fragment.favmatch

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.MatchEvent

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
interface FavMatchView : BaseView {
    fun onMatchData(matches: MatchEvent)
}