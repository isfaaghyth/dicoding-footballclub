package isfaaghyth.app.fotballclub.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.data.model.Player
import isfaaghyth.app.fotballclub.ui.playerdetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class PlayerAdapter(val players: List<Player>) : RecyclerView.Adapter<PlayerAdapter.Holder>() {

    override fun getItemCount(): Int = players.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val viewHolder = Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_player, parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent?.context?.startActivity<PlayerDetailActivity>("player" to players[position])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.setPlayer(players[position])
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPlayer = view.imgPlayer
        private val txtPlayerName = view.txtPlayerName

        fun setPlayer(player: Player) {
            Glide.with(itemView.context)
                    .load(player.strCutout)
                    .into(imgPlayer)
            txtPlayerName.text = player.strTeam
        }
    }

}