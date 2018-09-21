package isfaaghyth.app.fotballclub.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
data class League(
        @SerializedName("idLeague") var idLeague: String,
        @SerializedName("strLeague") var strLeague: String,
        @SerializedName("strSport") var strSport: String,
        @SerializedName("strLeagueAlternate") var strLeagueAlternate: String
) : Serializable