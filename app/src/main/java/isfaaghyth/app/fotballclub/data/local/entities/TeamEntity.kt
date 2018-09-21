package isfaaghyth.app.fotballclub.data.local.entities

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
data class TeamEntity(val id: Long?, val teamId: String?, val teamName: String?, val teamBadge: String?) {

    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }

}