package isfaaghyth.app.fotballclub.ui.main.fragment.favmatch

import android.content.Context
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.local.entities.MatchEntity
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.utils.reactive.ScheduleUtils
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class FavMatchPresenter(view: FavMatchView) : BasePresenter<FavMatchView>() {

    init { super.attachView(view) }

    fun getMatchFavorite(context: Context?) : List<MatchEntity> {
        lateinit var favoriteList : List<MatchEntity>
        context?.database?.use {
            val result = select(MatchEntity.TABLE_MATCH)
            val favorite = result.parseList(classParser<MatchEntity>())
            favoriteList = favorite
        }
        return favoriteList
    }

    fun getNextMatch(eventId: String) {
        view().showLoading()
        subscribe(getService().getMatchById(eventId)
                .compose(ScheduleUtils.set<MatchEvent>())
                .subscribe(
                        { res ->
                            run {
                                view().onMatchData(res)
                                onFinishRequest()
                            }
                        },
                        this::catchError
                ))
    }

}