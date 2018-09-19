package isfaaghyth.app.fotballclub.ui.teamdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.FavoriteEntities
import isfaaghyth.app.fotballclub.data.local.database
import isfaaghyth.app.fotballclub.data.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class TeamDetailPresenter(view: TeamDetailView) : BasePresenter<TeamDetailView>() {

    init { super.attachView(view) }

    fun isFavorite(context: Context, id: String): Boolean {
        var favorite: List<FavoriteEntities> = mutableListOf()
        context.database.use {
            val result = select(FavoriteEntities.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})", "id" to id)
            favorite = result.parseList(classParser())
        }
        return favorite.isEmpty()
    }

    fun addToFavorite(context: Context, team: Team) = try {
        context.database.use {
            insert(FavoriteEntities.TABLE_FAVORITE,
                    FavoriteEntities.TEAM_ID to team.idTeam,
                    FavoriteEntities.TEAM_NAME to team.strTeam,
                    FavoriteEntities.TEAM_BADGE to team.strTeamBadge)
        }
        view().onInfo("Added to Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

    fun removeFromFavorite(context: Context, id: String) = try {
        context.database.use {
            delete(FavoriteEntities.TABLE_FAVORITE, "(TEAM_ID = {id})", "id" to id)
        }
        view().onInfo("Removed from Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

}