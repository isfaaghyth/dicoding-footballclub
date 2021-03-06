package isfaaghyth.app.fotballclub.utils.reactive

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class AppSchedulerProvider : SchedulerProvider {
    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun trampoline(): Scheduler = Schedulers.trampoline()
    override fun newThread(): Scheduler = Schedulers.newThread()
    override fun io(): Scheduler = Schedulers.io()
}