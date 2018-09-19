package isfaaghyth.app.fotballclub.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch.NextMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPagerMain()
        bottomBarMain.setOnTabSelectListener(this::onBottomBarSelected)
    }

    private fun setupViewPagerMain() {
        viewpagerMain.setPagingEnabled(false)
        viewpagerMain.offscreenPageLimit = 2

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PrevMatchFragment())
        adapter.addFragment(NextMatchFragment())
        viewpagerMain.adapter = adapter
    }

    private fun onBottomBarSelected(resId: Int) {
        when (resId) {
            R.id.tab_prev_match -> viewpagerMain.currentItem = 0
            R.id.tab_next_match -> viewpagerMain.currentItem = 1
        }
    }

}
