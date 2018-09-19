package isfaaghyth.app.fotballclub.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */
object ConnectionUtils {

    fun isNetworkConnected(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}