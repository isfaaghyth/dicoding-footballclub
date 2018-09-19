package isfaaghyth.app.fotballclub.ui.teamdetail

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 * @TODO(still unused code, but maybe will be use it for the next submission)
 */
class TeamDetailActivity : BaseActivity<TeamDetailPresenter>(), TeamDetailView {

    override fun presenter(): TeamDetailPresenter = TeamDetailPresenter(this)

    override fun contentView(): Int = R.layout.activity_team_detail

    override fun onCreated() {
        showBackButton(true)
    }

}