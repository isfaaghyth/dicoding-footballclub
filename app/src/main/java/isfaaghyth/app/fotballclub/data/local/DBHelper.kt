package isfaaghyth.app.fotballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import isfaaghyth.app.fotballclub.data.local.entities.TeamEntity
import isfaaghyth.app.fotballclub.data.local.entities.MatchEntity
import org.jetbrains.anko.db.*

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 1) {

    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (instance == null) instance = DBHelper(ctx.applicationContext)
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(TeamEntity.TABLE_TEAM, true,
                TeamEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamEntity.TEAM_ID to TEXT + UNIQUE,
                TeamEntity.TEAM_NAME to TEXT,
                TeamEntity.TEAM_BADGE to TEXT)

        db.createTable(MatchEntity.TABLE_MATCH, true,
                MatchEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchEntity.EVENT_ID to TEXT + UNIQUE,
                MatchEntity.MATCH_HOME_ID to TEXT,
                MatchEntity.MATCH_AWAY_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(TeamEntity.TABLE_TEAM, true)
        db.dropTable(MatchEntity.TABLE_MATCH, true)
    }

}

val Context.database: DBHelper get() = DBHelper.getInstance(applicationContext)