package isfaaghyth.app.fotballclub.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class MainTestActivity : FragmentActivity() {

    private val CONTAINER_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        val frameLayout = FrameLayout(this)
        frameLayout.id = CONTAINER_ID
        setContentView(frameLayout, params)
    }

    fun addFragment(fragment: Fragment, id: String) {
        supportFragmentManager.beginTransaction()
                .add(CONTAINER_ID, fragment, id)
                .commit()
    }

}