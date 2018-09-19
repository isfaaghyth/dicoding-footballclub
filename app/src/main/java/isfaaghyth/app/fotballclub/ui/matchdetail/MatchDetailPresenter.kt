package isfaaghyth.app.fotballclub.ui.matchdetail

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.model.TeamRepository

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class MatchDetailPresenter(view: MatchDetailView) : BasePresenter<MatchDetailView>() {

    init { super.attachView(view) }

    fun getTeamsBadge(homeTeam: String, awayTeam: String) {
        view().showLoading()
        subscribe(
                Single.zip(
                        getService().teamDetail(homeTeam),
                        getService().teamDetail(awayTeam),
                        BiFunction<TeamRepository, TeamRepository, List<String>> { t1, t2 ->
                            listOf(t1.teams[0].strTeamBadge, t2.teams[0].strTeamBadge)
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { res ->
                                run {
                                    onFinishRequest()
                                    view().getTeamsBadge(res[0], res[1])
                                }
                            },
                            this::catchError
                        ))
    }

}