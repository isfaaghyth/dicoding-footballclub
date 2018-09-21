package isfaaghyth.app.fotballclub.ui.searchmatch

import isfaaghyth.app.fotballclub.base.BaseView
import isfaaghyth.app.fotballclub.data.model.Events

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
interface SearchMatchView : BaseView {
    fun onMatchData(matches: Events)
}