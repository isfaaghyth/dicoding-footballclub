package isfaaghyth.app.fotballclub.ui.search.match

import android.support.v7.widget.LinearLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.model.Events
import isfaaghyth.app.fotballclub.ui.adapter.MatchAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.activity_search_match.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class MatchActivity : BaseActivity<MatchPresenter>(), MatchView {

    override fun presenter(): MatchPresenter = MatchPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.activity_search_match

    override fun onCreated() {
        val querySearch = intent.getStringExtra("search")
        lstMatches.layoutManager = LinearLayoutManager(this)
        presenter().searchMatchByTeam(querySearch)
    }

    override fun onMatchData(matches: Events) = if (matches.event.isNotEmpty()) {
        lstMatches.adapter = MatchAdapter(matches.event)
    } else {
        onInfo(getString(R.string.not_found))
    }

}