package isfaaghyth.app.fotballclub.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import isfaaghyth.app.fotballclub.data.model.Club
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.imageResource

/**
 * Created by isfaaghyth on 9/17/18.
 * github: @isfaaghyth
 */
class FootballAdapter(private val clubs: ArrayList<Club> = arrayListOf(), private val listener: FootballListener)
    : RecyclerView.Adapter<FootballAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : Holder {
        val viewHolder = Holder(FootballComponent(parent.context).createView(AnkoContext.create(parent.context, parent)))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            listener.onClicked(clubs[position])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.imgClub.imageResource = clubs[position].icon
        holder.txtClubName.text = clubs[position].name
    }

    override fun getItemCount(): Int = clubs.size

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        var imgClub: ImageView = view.findViewById(FootballComponent.imgClubId)
        var txtClubName: TextView = view.findViewById(FootballComponent.txtClubNameId)
    }

}