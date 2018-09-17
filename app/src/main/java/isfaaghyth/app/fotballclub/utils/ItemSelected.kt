package isfaaghyth.app.fotballclub.utils

import android.content.Context
import android.support.annotation.AttrRes
import android.util.TypedValue
import isfaaghyth.app.fotballclub.R

/**
 * Created by isfaaghyth on 9/18/18.
 * github: @isfaaghyth
 */

val Context.selectableItemBackgroundResource: Int get() =
    getResourceIdAttribute(R.attr.selectableItemBackground)

fun Context.getResourceIdAttribute(@AttrRes attribute: Int) : Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attribute, typedValue, true)
    return typedValue.resourceId
}