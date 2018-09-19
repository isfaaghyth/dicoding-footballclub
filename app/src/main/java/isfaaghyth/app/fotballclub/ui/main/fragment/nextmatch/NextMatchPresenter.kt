package isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.utils.reactive.ScheduleUtils

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class NextMatchPresenter(view: NextMatchView) : BasePresenter<NextMatchView>() {

    init { super.attachView(view) }

    fun getNextMatch() {
        view().showLoading()
        subscribe(getService().nextMatch("4328")
                .compose(ScheduleUtils.set<MatchEvent>())
                .subscribe(
                        { res ->
                            run {
                                view().onNextMatchData(res)
                                onFinishRequest()
                            }
                        },
                        this::catchError
                ))
    }

}