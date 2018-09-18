package isfaaghyth.app.fotballclub.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.local.ClubManager
import isfaaghyth.app.fotballclub.data.model.Club
import isfaaghyth.app.fotballclub.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        val clubsRepository = ClubManager.get(this)
        lstFootball.layoutManager = LinearLayoutManager(this)
        lstFootball.adapter = FootballAdapter(clubsRepository, object : FootballListener {
            override fun onClicked(club: Club) = startActivity<DetailActivity>("club" to club)
        })
    }

}
