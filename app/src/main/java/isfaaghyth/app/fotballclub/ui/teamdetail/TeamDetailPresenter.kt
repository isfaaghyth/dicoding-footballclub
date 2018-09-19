package isfaaghyth.app.fotballclub.ui.teamdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import isfaaghyth.app.fotballclub.base.BasePresenter
import isfaaghyth.app.fotballclub.data.local.entities.FavoriteEntity
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
        var favorite: List<FavoriteEntity> = mutableListOf()
        context.database.use {
            val result = select(FavoriteEntity.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})", "id" to id)
            favorite = result.parseList(classParser())
        }
        return favorite.isEmpty()
    }

    fun addToFavorite(context: Context, team: Team) = try {
        context.database.use {
            insert(FavoriteEntity.TABLE_FAVORITE,
                    FavoriteEntity.TEAM_ID to team.idTeam,
                    FavoriteEntity.TEAM_NAME to team.strTeam,
                    FavoriteEntity.TEAM_BADGE to team.strTeamBadge)
        }
        view().onInfo("Added to Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

    fun removeFromFavorite(context: Context, id: String) = try {
        context.database.use {
            delete(FavoriteEntity.TABLE_FAVORITE, "(TEAM_ID = {id})", "id" to id)
        }
        view().onInfo("Removed from Favorite")
    } catch (e: SQLiteConstraintException) {
        view().onError(e.localizedMessage)
    }

}