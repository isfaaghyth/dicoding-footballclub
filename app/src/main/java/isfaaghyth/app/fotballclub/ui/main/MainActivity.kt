package isfaaghyth.app.fotballclub.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.ui.main.fragment.favmatch.FavMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch.NextMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPagerMain()
        onBottomBarSelected()
        bottomBarMain.selectedItemId = R.id.mnPrevMatch
    }

    private fun setupViewPagerMain() {
        viewpagerMain.setPagingEnabled(true)
        viewpagerMain.offscreenPageLimit = 3

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PrevMatchFragment())
        adapter.addFragment(NextMatchFragment())
        adapter.addFragment(FavMatchFragment())
        viewpagerMain.adapter = adapter
    }

    private fun onBottomBarSelected() = bottomBarMain.setOnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.mnPrevMatch -> viewpagerMain.currentItem = 0
            R.id.mnNextMatch -> viewpagerMain.currentItem = 1
            R.id.mnFavMatch -> viewpagerMain.currentItem = 2
        }
        true
    }

}
