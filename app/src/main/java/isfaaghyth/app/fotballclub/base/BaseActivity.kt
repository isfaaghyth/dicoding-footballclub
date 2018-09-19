package isfaaghyth.app.fotballclub.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun contentView(): Int
    protected abstract fun onCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        onCreated()
    }

    protected fun showBackButton(isShow: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isShow)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}