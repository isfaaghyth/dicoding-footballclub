package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Leagues
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.ui.adapter.MatchAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_prev_match.*

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class PrevMatchFragment : BaseFragment<PrevMatchPresenter>(), PrevMatchView {

    override fun presenter(): PrevMatchPresenter = PrevMatchPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.fragment_prev_match

    override fun onCreated() {
        lstPrevMatch.layoutManager = LinearLayoutManager(context())
        presenter().getLeagues()
    }

    override fun onPrevMatchData(matches: MatchEvent) {
        Log.d("onPrevMatchData", Gson().toJson(matches.events))
        lstPrevMatch.adapter = MatchAdapter(matches.events)
    }

    override fun onAllLeagues(leagues: Leagues) {
        val strLeagues = leagues.leagues.map { it.strLeague }
        val leaguesId = leagues.leagues.map { it.idLeague }
        setDropdownLeague(spinnerPrevMatch, strLeagues, object : SpinnerListener {
            override fun getLeague(position: Int) {
                presenter().getPrevMatchByLeagueId(leaguesId[position])
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

}