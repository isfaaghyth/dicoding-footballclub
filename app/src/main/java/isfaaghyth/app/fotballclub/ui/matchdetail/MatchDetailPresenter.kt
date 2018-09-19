package isfaaghyth.app.fotballclub.ui.matchdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.local.entities.MatchEntity
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.data.model.TeamRepository
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

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


    fun isFavorite(context: Context, id: String): Boolean {
        var favorite: List<MatchEntity> = mutableListOf()
        context.database.use {
            val result = select(MatchEntity.TABLE_MATCH)
                    .whereArgs("(EVENT_ID = {id})", "id" to id)
            favorite = result.parseList(classParser())
        }
        return favorite.isEmpty()
    }

    fun addToFavorite(context: Context, match: Match) = try {
        context.database.use {
            insert(MatchEntity.TABLE_MATCH,
                    MatchEntity.EVENT_ID to match.idEvent,
                    MatchEntity.MATCH_HOME_ID to match.idHomeTeam,
                    MatchEntity.MATCH_AWAY_ID to match.idAwayTeam)
        }
        view().onInfo("Added to Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

    fun removeFromFavorite(context: Context, id: String) = try {
        context.database.use {
            delete(MatchEntity.TABLE_MATCH, "(EVENT_ID = {id})", "id" to id)
        }
        view().onInfo("Removed from Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

}