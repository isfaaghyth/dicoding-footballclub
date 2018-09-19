package isfaaghyth.app.fotballclub.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.mateware.snacky.Snacky
import isfaaghyth.app.fotballclub.utils.ConnectionUtils
import isfaaghyth.app.fotballclub.utils.KeyboardUtils

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
abstract class BaseFragment<out P : BasePresenter<*>> : Fragment(), BaseView {

    private lateinit var presenter: P
    protected abstract fun presenter(): P
    protected abstract fun contentView(): Int
    protected abstract fun onCreated()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(contentView(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = presenter()
        onCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.dettachView()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String?) = Snacky.builder()
            .setActivity(activity)
            .setText(message)
            .setDuration(Snacky.LENGTH_LONG)
            .error()
            .show()

    override fun onError(resId: Int) = onError(getString(resId))

    override fun onInfo(message: String?)  = Snacky.builder()
            .setActivity(activity)
            .setText(message)
            .setDuration(Snacky.LENGTH_LONG)
            .info()
            .show()

    override fun onInfo(resId: Int) = onInfo(getString(resId))

    override fun isNetworkConnected(): Boolean? = ConnectionUtils.isNetworkConnected(context)

    override fun hideKeyboard() = KeyboardUtils.hideSoftInput(activity)

    override fun context(): Context? = context

}