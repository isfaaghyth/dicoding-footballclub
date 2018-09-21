package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuInflater
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
import android.widget.SearchView
import android.widget.Spinner
import isfaaghyth.app.fotballclub.ui.search.team.TeamActivity
import org.jetbrains.anko.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamsFragment : BaseFragment<TeamsPresenter>(), TeamsView {

    override fun presenter(): TeamsPresenter = TeamsPresenter(this, AppSchedulerProvider())

    override fun contentView(): Int = R.layout.fragment_teams

    override fun onCreated() {
        setHasOptionsMenu(true)
        lstTeams.layoutManager = GridLayoutManager(context(), 3)
        presenter().getLeagues()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val searchView = menu?.findItem(R.id.mnSearch)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                activity?.startActivity<TeamActivity>("query" to query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })
        super.onPrepareOptionsMenu(menu)
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