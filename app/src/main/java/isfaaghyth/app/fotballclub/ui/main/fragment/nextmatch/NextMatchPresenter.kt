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

    fun getNextMatch() {
        view().showLoading()
        subscribe(getService().nextMatch("4328")
                .observeOn(subscriber.ui())
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

}