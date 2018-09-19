package isfaaghyth.app.fotballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import isfaaghyth.app.fotballclub.data.local.entities.FavoriteEntity
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
        db.createTable(FavoriteEntity.TABLE_FAVORITE, true,
                FavoriteEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEntity.TEAM_ID to TEXT + UNIQUE,
                FavoriteEntity.TEAM_NAME to TEXT,
                FavoriteEntity.TEAM_BADGE to TEXT)

        db.createTable(MatchEntity.TABLE_MATCH, true,
                MatchEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchEntity.EVENT_ID to TEXT + UNIQUE,
                MatchEntity.MATCH_HOME_ID to TEXT,
                MatchEntity.MATCH_AWAY_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteEntity.TABLE_FAVORITE, true)
        db.dropTable(MatchEntity.TABLE_MATCH, true)
    }

}

val Context.database: DBHelper get() = DBHelper.getInstance(applicationContext)