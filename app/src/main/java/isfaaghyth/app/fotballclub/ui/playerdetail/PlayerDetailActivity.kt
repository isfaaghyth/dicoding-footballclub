package isfaaghyth.app.fotballclub.ui.playerdetail

import com.bumptech.glide.Glide
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseActivity
import isfaaghyth.app.fotballclub.data.model.Player
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.activity_player_detail.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class PlayerDetailActivity : BaseActivity<PlayerDetailPresenter>(), PlayerDetailView {

    override fun presenter(): PlayerDetailPresenter = PlayerDetailPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.activity_player_detail

    private lateinit var player: Player

    override fun onCreated() {
        showBackButton(true)
        player = intent.getSerializableExtra("player") as Player
        setPlayerDetail(player)
    }

    fun setPlayerDetail(player: Player) {
        Glide.with(this).load(player.strCutout).into(imgPlayer)
        txtPlayerName.text = player.strPlayer
        txtOverview.text = player.strDescriptionEN
    }

}