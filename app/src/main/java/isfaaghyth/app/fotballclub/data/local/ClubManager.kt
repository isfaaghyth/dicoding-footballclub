package isfaaghyth.app.fotballclub.data.local

import android.content.Context
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.model.Club

/**
 * Created by isfaaghyth on 9/17/18.
 * github: @isfaaghyth
 */
class ClubManager {

    companion object {
        fun get(context: Context) : ArrayList<Club> {
            val clubs = ArrayList<Club>()
            clubs.add(Club(R.drawable.madrid,
                    context.getString(R.string.madrid),
                    context.getString(R.string.madrid_desc)))
            return clubs
        }
    }

}