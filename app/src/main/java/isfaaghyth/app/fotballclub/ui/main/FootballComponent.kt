package isfaaghyth.app.fotballclub.ui.main

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import isfaaghyth.app.fotballclub.utils.selectableItemBackgroundResource
import org.jetbrains.anko.*

/**
 * Created by isfaaghyth on 9/17/18.
 * github: @isfaaghyth
 */
class FootballComponent(val context: Context) : AnkoComponent<ViewGroup> {

    companion object {
        val itemId = 1
        val imgClubId = 2
        val txtClubNameId = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(width = matchParent)

            id = itemId
            isClickable = true
            gravity = Gravity.CENTER_VERTICAL
            orientation = LinearLayout.HORIZONTAL
            padding = dip(10)
            backgroundResource = context.selectableItemBackgroundResource

            imageView {
                id = imgClubId
            }.lparams(width = dip(40), height = dip(40))

            textView {
                id = txtClubNameId
                textSize = 16f //sp
            }.lparams {
                marginStart = dip(10)
            }

        }
    }

}