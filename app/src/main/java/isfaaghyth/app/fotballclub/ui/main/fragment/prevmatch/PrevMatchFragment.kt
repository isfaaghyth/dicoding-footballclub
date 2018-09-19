package isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class PrevMatchFragment : BaseFragment<PrevMatchPresenter>(), PrevMatchView {

    override fun presenter(): PrevMatchPresenter = PrevMatchPresenter(this)
    override fun contentView(): Int = R.layout.fragment_prev_match

    override fun onCreated() {

    }

}