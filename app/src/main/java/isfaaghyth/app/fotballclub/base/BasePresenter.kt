package isfaaghyth.app.fotballclub.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import isfaaghyth.app.fotballclub.network.Network
import isfaaghyth.app.fotballclub.network.Routes
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
open class BasePresenter<V: BaseView>() : BasePresenterImpl<V> {

    private lateinit var view: V
    private lateinit var routes: Routes
    private lateinit var composite: CompositeDisposable

    fun subscribe(disposable: Disposable) {
        composite = CompositeDisposable()
        view().showLoading()
        composite.add(disposable)
    }

    override fun onFinishRequest() = view().hideLoading()

    override fun catchError(error: Throwable) = try {
        view().hideLoading()
        when (error) {
            is HttpException -> view().onError(error.message())
            is SocketTimeoutException -> view().onError(error.message)
            is IOException -> view().onError(error.message)
            else -> view().onError(error.message)
        }
    } catch (e: Exception) {
        view().onError("error!")
    }

    override fun attachView(view: V) {
        this.view = view
        routes = Network.getService()
    }

    override fun dettachView() {
        if (composite.isDisposed) {
            composite.clear()
        }
    }

    override fun view(): V = view

}