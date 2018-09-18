package isfaaghyth.app.fotballclub.data.local

import android.content.Context
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.model.Club

/**
 * Created by isfaaghyth on 9/17/18.
 * github: @isfaaghyth
 */
object ClubManager {

    fun get(context: Context) : ArrayList<Club> {
        val clubs = ArrayList<Club>()
        clubs.add(Club(R.drawable.madrid, context.getString(R.string.club_madrid), context.getString(R.string.madrid_desc)))
        clubs.add(Club(R.drawable.barcelona, context.getString(R.string.club_barcelona), context.getString(R.string.barcelona_desc)))
        clubs.add(Club(R.drawable.juventus, context.getString(R.string.club_juventus), context.getString(R.string.juventus_desc)))
        clubs.add(Club(R.drawable.bayern, context.getString(R.string.club_bayern), context.getString(R.string.bayern_desc)))
        clubs.add(Club(R.drawable.chelsea, context.getString(R.string.club_chelsea), context.getString(R.string.chelsea_desc)))
        return clubs
    }

}