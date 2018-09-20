package isfaaghyth.app.fotballclub.utils.reactive

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 9/20/18.
 * github: @isfaaghyth
 */
class TestSchedulerProvider: SchedulerProvider {
    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun computation(): Scheduler = Schedulers.trampoline()
    override fun trampoline(): Scheduler = Schedulers.trampoline()
    override fun newThread(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
}