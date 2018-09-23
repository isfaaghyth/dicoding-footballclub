package isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch

import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Leagues
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.ui.adapter.MatchAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.toast
import java.text.SimpleDateFormat

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class NextMatchFragment : BaseFragment<NextMatchPresenter>(), NextMatchView {

    override fun presenter(): NextMatchPresenter = NextMatchPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.fragment_next_match

    private lateinit var adapter: MatchAdapter

    override fun onCreated() {
        lstNextMatch.layoutManager = LinearLayoutManager(context())
        presenter().getLeagues()
    }

    override fun onNextMatchData(matches: MatchEvent) {
        adapter = MatchAdapter(matches.events)
        lstNextMatch.adapter = adapter
    }

    override fun onAllLeagues(leagues: Leagues) {
        val strLeagues = leagues.leagues.map { it.strLeague }
        val leaguesId = leagues.leagues.map { it.idLeague }
        setDropdownLeague(spinnerNextMatch, strLeagues, object : SpinnerListener {
            override fun getLeague(position: Int) {
                presenter().getNextMatchByLeagueId(leaguesId[position])
            }
        })
    }

    private fun setDropdownLeague(spinner: Spinner, item: List<String>, listener: SpinnerListener) {
        spinner.adapter = ArrayAdapter(context(), android.R.layout.simple_spinner_dropdown_item, item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                listener.getLeague(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) = Unit
        }
    }

    interface SpinnerListener {
        fun getLeague(position: Int)
    }

    private fun getGoogleCalendarId(): Long {
        val projection = arrayOf(CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME, CalendarContract.Calendars.ACCOUNT_NAME, CalendarContract.Calendars.ACCOUNT_TYPE)
        val calCursor = this.activity?.contentResolver
                ?.query(CalendarContract.Calendars.CONTENT_URI, projection,
                        CalendarContract.Calendars.VISIBLE + " = 1", null, CalendarContract.Calendars._ID + " ASC")
        if (calCursor!!.moveToFirst()) {
            do {
                val id = calCursor.getLong(0)
                return id
            } while (calCursor.moveToNext())
        }
        return -1

    }

    private fun addEventToGoogleCalendar(match: Match) {
        if(match.intHomeScore != null) toast("This event has passed, please choose the upcoming one!")

        val calId = getGoogleCalendarId()

        if (calId == -1L) {
            Toast.makeText(context(), "Somethings went wrong, try again!", Toast.LENGTH_SHORT).show()
            return
        }

        val title = match.strEvent
        val clock = match.strTime?.split("+")?.get(0)
        val dt = match.dateEvent
        val dateWithClock = "$dt $clock"
        val simpleDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val date = simpleDate.parse(dateWithClock)

        val timeInMillis = date.time

        //add end time to 90 minutes
        val end = timeInMillis + 5400000
        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra(CalendarContract.Events.TITLE, title)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Television")
        startActivity(intent)
    }

}