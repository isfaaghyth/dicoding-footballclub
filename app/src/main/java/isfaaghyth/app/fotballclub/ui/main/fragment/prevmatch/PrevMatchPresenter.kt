package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import android.util.Log
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.utils.reactive.ScheduleUtils
import isfaaghyth.app.fotballclub.utils.test.SchedulerProvider
import isfaaghyth.app.fotballclub.utils.test.TestSchedulerProvider

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
open class PrevMatchPresenter(view: PrevMatchView) : BasePresenter<PrevMatchView>() {

    private var schedule = TestSchedulerProvider()

    init {
        super.attachView(view)
    }

    fun getPrevMatch() {
        view().showLoading()
        subscribe(getService().prevMatch("4328")
                .observeOn(schedule.ui())
                .subscribeOn(schedule.io())
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