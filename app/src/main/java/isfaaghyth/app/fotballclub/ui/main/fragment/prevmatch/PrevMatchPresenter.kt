package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
open class PrevMatchPresenter(val view: PrevMatchView, private val scheduler: SchedulerProvider)
    : BasePresenter<PrevMatchView>() {

    init {
        super.attachView(view)
    }

    fun getPrevMatch() {
        view().showLoading()
        subscribe(getService().prevMatch("4328")
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe(
                        { res ->
                            run {
                                view().onPrevMatchData(res)
                                onFinishRequest()
                            }
                        },
                        this::catchError
                ))
    }

}