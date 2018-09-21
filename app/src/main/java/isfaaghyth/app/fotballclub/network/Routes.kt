package isfaaghyth.app.fotballclub.network

import io.reactivex.Flowable
import io.reactivex.Single
import isfaaghyth.app.fotballclub.data.model.*
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
    fun teamDetail(@Query("id") id: String) : Single<Teams>

    @GET("api/v1/json/1/lookupevent.php") //4328
    fun getMatchById(@Query("id") id: String) : Flowable<MatchEvent>

    @GET("api/v1/json/1/search_all_teams.php")
    fun getTeamByLeague(@Query("l") league: String) : Flowable<Teams>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamById(@Query("id") teamId: String) : Flowable<Teams>

    @GET("api/v1/json/1/all_leagues.php")
    fun getAllLeagues() : Single<Leagues>

    @GET("api/v1/json/1/lookup_all_players.php")
    fun getPlayersByTeam(@Query("id") teamId: String) : Flowable<Players>

    @GET("api/v1/json/1/searchteams.php")
    fun searchTeamByName(@Query("t") teamName: String) : Single<Teams>

    @GET("api/v1/json/1/searchevents.php")
    fun searchMatchByTeam(@Query("e") teamName: String) : Single<Events>

}