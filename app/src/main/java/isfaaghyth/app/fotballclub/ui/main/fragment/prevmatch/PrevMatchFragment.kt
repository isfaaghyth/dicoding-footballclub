package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Match
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
        presenter().getPrevMatch("4328")
    }

    override fun onPrevMatchData(matches: MatchEvent) {
        Log.d("onPrevMatchData", Gson().toJson(matches.events))
        lstPrevMatch.adapter = MatchAdapter(matches.events)
    }

}