package isfaaghyth.app.fotballclub.ui.detail

import android.content.Intent
import android.graphics.Typeface
import android.view.Gravity
import isfaaghyth.app.fotballclub.data.model.Club
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*

/**
 * Created by isfaaghyth on 9/17/18.
 * github: @isfaaghyth
 */

class DetailComponent(private var intent: Intent) : AnkoComponent<DetailActivity> {

    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
        val club = intent.getSerializableExtra("club") as Club

        verticalLayout {
            imageView(club.icon).lparams(width = matchParent) {
                padding = dip(20)
                margin = dip(15)
            }
            textView(club.name) {
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
                gravity = Gravity.CENTER_HORIZONTAL
            }
            textView(club.description) {
                textAlignment = Gravity.CENTER
            }
        }

    }

}