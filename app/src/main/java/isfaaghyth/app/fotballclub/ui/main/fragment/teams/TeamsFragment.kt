package isfaaghyth.app.fotballclub.ui.main.fragment.teams

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class TeamsFragment : BaseFragment<TeamsPresenter>(), TeamsView {

    override fun presenter(): TeamsPresenter = TeamsPresenter(this)

    override fun contentView(): Int = R.layout.fragment_teams_detail

    override fun onCreated() {

    }

}