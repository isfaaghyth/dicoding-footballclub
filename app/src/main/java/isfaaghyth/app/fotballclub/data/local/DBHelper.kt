package isfaaghyth.app.fotballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

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
        db.createTable(FavoriteEntities.TABLE_FAVORITE, true,
                FavoriteEntities.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEntities.TEAM_ID to TEXT + UNIQUE,
                FavoriteEntities.TEAM_NAME to TEXT,
                FavoriteEntities.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteEntities.TABLE_FAVORITE, true)
    }
}

val Context.database: DBHelper get() = DBHelper.getInstance(applicationContext)