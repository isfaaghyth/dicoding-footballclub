package isfaaghyth.app.fotballclub.ui.main.fragment.matches

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.ui.main.ViewPagerAdapter
import isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch.NextMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch.PrevMatchFragment
import isfaaghyth.app.fotballclub.ui.searchmatch.SearchMatchActivity
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.startActivity

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class MatchesFragment : BaseFragment<MatchesPresenter>(), MatchesView {

    override fun presenter(): MatchesPresenter = MatchesPresenter(this)
    override fun contentView(): Int = R.layout.fragment_matches

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.mnSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Match"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                activity?.startActivity<SearchMatchActivity>("query" to query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })
        super.onCreateOptionsMenu(  menu, inflater)
    }

}