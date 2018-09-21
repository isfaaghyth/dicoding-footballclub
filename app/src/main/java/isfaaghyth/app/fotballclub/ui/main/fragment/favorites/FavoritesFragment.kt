package isfaaghyth.app.fotballclub.ui.main.fragment.favorites

import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.ui.main.ViewPagerAdapter
import isfaaghyth.app.fotballclub.ui.main.fragment.favmatch.FavMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.favteam.FavTeamFragment
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class FavoritesFragment : BaseFragment<FavoritesPresenter>(), FavoritesView {

    override fun presenter(): FavoritesPresenter = FavoritesPresenter(this)
    override fun contentView(): Int = R.layout.fragment_favorites

    override fun onCreated() {
        setupViewPagerMatches()
        tabLayoutFavorites.setupWithViewPager(viewpagerFavorites)
    }

    private fun setupViewPagerMatches() {
        viewpagerFavorites.setPagingEnabled(true)
        viewpagerFavorites.offscreenPageLimit = 2
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavMatchFragment(), "Matches")
        adapter.addFragment(FavTeamFragment(), "Teams")
        viewpagerFavorites.adapter = adapter
    }

}