package isfaaghyth.app.fotballclub.network

import io.reactivex.Single
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.data.model.TeamRepository
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
interface Routes {

    @GET("api/v1/json/1/eventspastleague.php") //4328
    fun prevMatch(@Query("id") id: String) : Single<MatchEvent>

    @GET("api/v1/json/1/eventsnextleague.php") //4328
    fun nextMatch(@Query("id") id: String) : Single<MatchEvent>

    @GET("api/v1/json/1/lookupteam.php")
    fun teamDetail(@Query("id") id: String) : Single<TeamRepository>

    @GET("api/v1/json/1/lookupevent.php") //4328
    fun getMatchById(@Query("id") id: String) : Single<MatchEvent>

}