package isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch

import android.support.v7.widget.LinearLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.MatchEvent
import isfaaghyth.app.fotballclub.ui.adapter.MatchAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class NextMatchFragment : BaseFragment<NextMatchPresenter>(), NextMatchView {

    override fun presenter(): NextMatchPresenter = NextMatchPresenter(this)
    override fun contentView(): Int = R.layout.fragment_next_match

    override fun onCreated() {
        lstNextMatch.layoutManager = LinearLayoutManager(context())
        presenter().getNextMatch()
    }

    override fun onNextMatchData(matches: MatchEvent) {
        lstNextMatch.adapter = MatchAdapter(matches.events)
    }

}