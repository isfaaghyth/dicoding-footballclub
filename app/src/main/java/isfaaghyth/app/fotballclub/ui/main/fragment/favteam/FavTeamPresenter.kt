package isfaaghyth.app.fotballclub.ui.main.fragment.favteam

import android.content.Context
import io.reactivex.Single
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.local.entities.TeamEntity
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class FavTeamPresenter(val view: FavTeamView, val subscriber: SchedulerProvider) : BasePresenter<FavTeamView>() {

    init { super.attachView(view) }

    private fun getTeamFavorite(context: Context?) : Single<List<TeamEntity>> {
        lateinit var favoriteList : List<TeamEntity>
        context?.database?.use {
            val result = select(TeamEntity.TABLE_TEAM)
            val favorite = result.parseList(classParser<TeamEntity>())
            favoriteList = favorite
        }
        return Single.just(favoriteList)
    }

    fun getTeam(context: Context?) {
        view().showLoading()
        subscribe(getTeamFavorite(context)
                .flattenAsFlowable{ it }
                .flatMap({ getService().getTeamById(it.teamId.toString()) })
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe(
                        { res ->
                            run {
                                view().getTeam(res)
                                onFinishRequest()
                            }
                        },
                        this::catchError
                ))
    }

}