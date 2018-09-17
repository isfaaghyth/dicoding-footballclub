package isfaaghyth.app.fotballclub.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import isfaaghyth.app.fotballclub.BuildConfig
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.local.ClubManager
import isfaaghyth.app.fotballclub.data.model.Club
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        lstFootball.layoutManager = LinearLayoutManager(this)
        lstFootball.adapter = FootballAdapter(ClubManager.get(this), object : FootballListener {
            override fun onClicked(club: Club) {
                Log.d("MainActivity", club.name)
            }
        })
    }

}
