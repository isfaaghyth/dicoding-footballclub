package isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class NextMatchFragment : BaseFragment<NextMatchPresenter>(), NextMatchView {

    override fun presenter(): NextMatchPresenter = NextMatchPresenter(this)
    override fun contentView(): Int = R.layout.fragment_next_match

    override fun onCreated() {

    }

}