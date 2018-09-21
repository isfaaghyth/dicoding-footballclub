package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamsPresenter(view: TeamsView, private val subscriber: SchedulerProvider): BasePresenter<TeamsView>() {

    init { super.attachView(view) }

    fun getTeamByLeague(league: String) {
        subscribe(getService().getTeamByLeague(league)
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onTeamsData(res)
                }, this::catchError))
    }

    fun getLeagues() {
        subscribe(getService().getAllLeagues()
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onAllLeagues(res)
                }, this::catchError))
    }

    fun searchTeamByName(teamName: String) {
        subscribe(getService().searchTeamByName(teamName)
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onTeamsData(res)
                }, this::catchError))
    }

}