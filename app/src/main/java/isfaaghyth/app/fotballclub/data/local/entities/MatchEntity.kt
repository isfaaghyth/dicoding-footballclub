package isfaaghyth.app.fotballclub.data.local.entities

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
data class MatchEntity(val id: Long?, val eventId: String?, val matchHomeId: String?, val matchAwayId: String?) {

    companion object {
        const val TABLE_MATCH: String = "TABLE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val MATCH_HOME_ID: String = "MATCH_HOME_ID"
        const val MATCH_AWAY_ID: String = "MATCH_AWAY_ID"
    }

}