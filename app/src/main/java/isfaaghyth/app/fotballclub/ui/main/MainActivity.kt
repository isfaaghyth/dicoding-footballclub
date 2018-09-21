package isfaaghyth.app.fotballclub.ui.main

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.ui.main.fragment.favmatch.FavMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.matches.MatchesFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.nextmatch.NextMatchFragment
import isfaaghyth.app.fotballclub.ui.main.fragment.prevmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import android.Manifest.permission
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.READ_CONTACTS
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import isfaaghyth.app.fotballclub.ui.main.fragment.teams.TeamsFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPagerMain()
        onBottomBarSelected()
        bottomBarMain.selectedItemId = R.id.mnPrevMatch

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_CALENDAR,
                        Manifest.permission.READ_CALENDAR
                ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) = Unit
            override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) = Unit
        }).check()
    }

    private fun setupViewPagerMain() {
        viewpagerMain.setPagingEnabled(true)
        viewpagerMain.offscreenPageLimit = 3

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MatchesFragment(), "Matches")
        adapter.addFragment(TeamsFragment(), "Teams")
        adapter.addFragment(FavMatchFragment(), "Fav Match")
        viewpagerMain.adapter = adapter
    }

    private fun onBottomBarSelected() = bottomBarMain.setOnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.mnMatches -> viewpagerMain.currentItem = 0
            R.id.mnTeams -> viewpagerMain.currentItem = 1
            R.id.mnFavMatch -> viewpagerMain.currentItem = 2
        }
        true
    }

}
