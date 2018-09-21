package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import android.support.v7.widget.GridLayoutManager
import android.view.View
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Leagues
import isfaaghyth.app.fotballclub.data.model.Teams
import isfaaghyth.app.fotballclub.ui.adapter.TeamAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_teams.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamsFragment : BaseFragment<TeamsPresenter>(), TeamsView {

    override fun presenter(): TeamsPresenter = TeamsPresenter(this, AppSchedulerProvider())

    override fun contentView(): Int = R.layout.fragment_teams

    override fun onCreated() {
        lstTeams.layoutManager = GridLayoutManager(context(), 3)
        presenter().getLeagues()
    }

    override fun onTeamsData(team: Teams) {
        lstTeams.adapter = TeamAdapter(team.teams)
    }

    override fun onAllLeagues(leagues: Leagues) {
        val strLeagues = leagues.leagues.map { it.strLeague }
        setDropdownLeague(spinnerLeagues, strLeagues, object : SpinnerListener {
            override fun getLeague(position: Int) {
                presenter().getTeamByLeague(strLeagues[position])
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