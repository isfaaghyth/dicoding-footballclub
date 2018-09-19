package isfaaghyth.app.fotballclub.base

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
interface BasePresenterImpl<V> {
    fun onFinishRequest()
    fun catchError(error: Throwable)
    fun attachView(view: V)
    fun dettachView()
    fun view(): V
}