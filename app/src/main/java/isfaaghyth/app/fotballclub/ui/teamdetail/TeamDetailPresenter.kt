package isfaaghyth.app.fotballclub.ui.teamdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.entities.TeamEntity
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.model.Team
import isfaaghyth.app.fotballclub.utils.reactive.SchedulerProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class TeamDetailPresenter(view: TeamDetailView, private val subscriber: SchedulerProvider) : BasePresenter<TeamDetailView>() {

    init { super.attachView(view) }

    fun getPlayerByTeam(teamId: String) {
        subscribe(getService().getPlayersByTeam(teamId)
                .observeOn(subscriber.mainThread())
                .subscribeOn(subscriber.io())
                .subscribe({
                    res -> view().onAllPlayers(res)
                }, this::catchError))
    }

    fun isFavorite(context: Context, id: String): Boolean {
        var team: List<TeamEntity> = mutableListOf()
        context.database.use {
            val result = select(TeamEntity.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})", "id" to id)
            team = result.parseList(classParser())
        }
        return team.isEmpty()
    }

    fun addToFavorite(context: Context, team: Team) = try {
        context.database.use {
            insert(TeamEntity.TABLE_TEAM,
                    TeamEntity.TEAM_ID to team.idTeam,
                    TeamEntity.TEAM_NAME to team.strTeam,
                    TeamEntity.TEAM_BADGE to team.strTeamBadge)
        }
        view().onInfo("Added to Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

    fun removeFromFavorite(context: Context, id: String) = try {
        context.database.use {
            delete(TeamEntity.TABLE_TEAM, "(TEAM_ID = {id})", "id" to id)
        }
        view().onInfo("Removed from Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

}