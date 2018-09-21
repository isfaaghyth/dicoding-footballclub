package isfaaghyth.app.fotballclub.ui.search.match

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class MatchPresenter(val view: MatchView, private val subscriber: SchedulerProvider) : BasePresenter<MatchView>() {

    init { super.attachView(view) }

    fun searchMatchByTeam(teamName: String) {
        subscribe(getService().searchMatchByTeam(teamName)
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onMatchData(res)
                }, this::catchError))
    }

}