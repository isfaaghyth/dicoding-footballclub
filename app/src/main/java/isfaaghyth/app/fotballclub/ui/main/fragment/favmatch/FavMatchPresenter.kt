package isfaaghyth.app.fotballclub.ui.main.fragment.favmatch

import android.content.Context
import io.reactivex.Single
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.local.entities.MatchEntity
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class FavMatchPresenter(val view: FavMatchView, private val subscriber: SchedulerProvider)
    : BasePresenter<FavMatchView>() {

    init { super.attachView(view) }

    private fun getMatchFavorite(context: Context?) : Single<List<MatchEntity>> {
        lateinit var favoriteList : List<MatchEntity>
        context?.database?.use {
            val result = select(MatchEntity.TABLE_MATCH)
            val favorite = result.parseList(classParser<MatchEntity>())
            favoriteList = favorite
        }
        return Single.just(favoriteList)
    }

    fun getNextMatch(context: Context?) {
        view().showLoading()
        subscribe(getMatchFavorite(context)
                .flattenAsFlowable{ it }
                .flatMap({ getService().getMatchById(it.eventId.toString()) })
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
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