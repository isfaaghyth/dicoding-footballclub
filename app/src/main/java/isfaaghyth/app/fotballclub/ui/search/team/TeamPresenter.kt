package isfaaghyth.app.fotballclub.ui.search.team

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamPresenter(val view: TeamView, private val subscriber: SchedulerProvider) : BasePresenter<TeamView>() {

    init { super.attachView(view) }

    fun searchTeamByName(teamName: String) {
        subscribe(getService().searchTeamByName(teamName)
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onTeamsData(res)
                }, this::catchError))
    }

}