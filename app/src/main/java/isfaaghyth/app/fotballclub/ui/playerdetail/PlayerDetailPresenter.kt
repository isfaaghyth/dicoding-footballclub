package isfaaghyth.app.fotballclub.ui.playerdetail

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class PlayerDetailPresenter(val view: PlayerDetailView, val scheduler: SchedulerProvider)
    : BasePresenter<PlayerDetailView>() {

    init { super.attachView(view) }

}