package isfaaghyth.app.fotballclub.ui.main.fragment.favmatch

import android.support.v7.widget.LinearLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Match
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.ui.adapter.MatchAdapter
import kotlinx.android.synthetic.main.fragment_fav_match.*

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class FavMatchFragment : BaseFragment<FavMatchPresenter>(), FavMatchView {

    override fun presenter(): FavMatchPresenter = FavMatchPresenter(this)
    override fun contentView(): Int = R.layout.fragment_fav_match

    private var events : MutableList<Match> = mutableListOf()
    private var adapter = MatchAdapter(events)

    override fun onCreated() {
        lstFavMatch.layoutManager = LinearLayoutManager(context())
        lstFavMatch.adapter = adapter
        swipeRefresh.setOnRefreshListener { getFavoriteLocal() }
        swipeRefresh.post({
            swipeRefresh.isRefreshing = true
            getFavoriteLocal()
        })
    }

    private fun getFavoriteLocal() {
        events.clear()
        val data = presenter().getMatchFavorite(context())
        for (i in data) {
            presenter().getNextMatch(i.eventId.toString())
        }
    }

    override fun onMatchData(matches: MatchEvent) {
        swipeRefresh.isRefreshing = false //TODO(harusnya masuk ke basePresenter)
        events.add(matches.events[0])
        adapter.notifyDataSetChanged()
    }
}