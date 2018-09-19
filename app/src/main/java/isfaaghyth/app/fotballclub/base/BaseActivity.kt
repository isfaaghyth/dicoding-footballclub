package isfaaghyth.app.fotballclub.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import de.mateware.snacky.Snacky
import isfaaghyth.app.fotballclub.utils.ConnectionUtils
import isfaaghyth.app.fotballclub.utils.KeyboardUtils

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
abstract class BaseActivity<out P: BasePresenter<*>> : AppCompatActivity(), BaseView {

    private lateinit var presenter: P
    protected abstract fun presenter(): P
    protected abstract fun contentView(): Int
    protected abstract fun onCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        presenter = presenter()
        onCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
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

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun onError(message: String?) = Snacky.builder()
            .setActivity(this)
            .setText(message)
            .setDuration(Snacky.LENGTH_LONG)
            .error()
            .show()

    override fun onError(resId: Int) = onError(getString(resId))

    override fun onInfo(message: String?)  = Snacky.builder()
            .setActivity(this)
            .setText(message)
            .setDuration(Snacky.LENGTH_LONG)
            .info()
            .show()

    override fun onInfo(resId: Int) = onInfo(getString(resId))

    override fun isNetworkConnected(): Boolean? = ConnectionUtils.isNetworkConnected(this)

    override fun hideKeyboard() = KeyboardUtils.hideSoftInput(this)

    override fun context(): Context? = this

}