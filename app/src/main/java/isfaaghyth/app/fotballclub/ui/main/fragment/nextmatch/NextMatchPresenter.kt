package isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class NextMatchPresenter(val view: NextMatchView, private val subscriber: SchedulerProvider)
    : BasePresenter<NextMatchView>() {

    init { super.attachView(view) }

    fun getNextMatchByLeagueId(matchId: String) {
        view().showLoading()
        subscribe(getService().getMatchNewByLeagueId(matchId)
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
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

    fun getLeagues() {
        subscribe(getService().getAllLeagues()
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onAllLeagues(res)
                }, this::catchError))
    }

}