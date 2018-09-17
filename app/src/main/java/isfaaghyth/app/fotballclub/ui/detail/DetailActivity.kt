package isfaaghyth.app.fotballclub.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import isfaaghyth.app.fotballclub.R
import org.jetbrains.anko.setContentView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailComponent(intent).setContentView(this)
    }

}
