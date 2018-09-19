package isfaaghyth.app.fotballclub.utils.reactive

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
class MainScheduler<T>: SchedulerTransformer<T>(Schedulers.io(), AndroidSchedulers.mainThread())