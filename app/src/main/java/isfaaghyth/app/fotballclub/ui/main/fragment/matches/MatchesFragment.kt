package isfaaghyth.app.fotballclub.ui.main.fragment.matches

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.ui.main.ViewPagerAdapter
import isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch.NextMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.fragment_matches.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class MatchesFragment : BaseFragment<MatchesPresenter>(), MatchesView {

    override fun presenter(): MatchesPresenter = MatchesPresenter(this)
    override fun contentView(): Int = R.layout.fragment_matches

    override fun onCreated() {
        setupViewPagerMatches()
        tabLayoutMatches.setupWithViewPager(viewpagerMatches)
    }

    private fun setupViewPagerMatches() {
        viewpagerMatches.setPagingEnabled(true)
        viewpagerMatches.offscreenPageLimit = 2
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PrevMatchFragment(), "Latest")
        adapter.addFragment(NextMatchFragment(), "Next")
        viewpagerMatches.adapter = adapter
    }

}