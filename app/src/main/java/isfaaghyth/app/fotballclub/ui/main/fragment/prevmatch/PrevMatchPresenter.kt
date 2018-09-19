package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.utils.reactive.ScheduleUtils

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class PrevMatchPresenter(view: PrevMatchView) : BasePresenter<PrevMatchView>() {

    init { super.attachView(view) }

    fun getPrevMatch() {
        subscribe(getService().prevMatch("4328")
                .compose(ScheduleUtils.set<MatchEvent>())
                .subscribe(
                        { res -> view().onPrevMatchData(res) },
                        { error: Throwable -> this.catchError(error) }
                ))
    }

}