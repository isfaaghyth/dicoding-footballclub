package isfaaghyth.app.fotballclub.utils.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by isfaaghyth on 9/19/18.
 * github: @isfaaghyth
 */

class CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var enable: Boolean = false

    init { this.enable = true }

    override fun onTouchEvent(event: MotionEvent): Boolean = this.enable && super.onTouchEvent(event)
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean = this.enable && super.onInterceptTouchEvent(event)

    fun setPagingEnabled(enabled: Boolean) {
        this.enable = enabled
    }

}
