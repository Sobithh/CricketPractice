package com.mindorks.framework.taskpracticecricket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.taskpracticecricket.databinding.ScoreboardRowBinding
import com.mindorks.framework.taskpracticecricket.model2.MainMatchDetail
import com.mindorks.framework.taskpracticecricket.model2.Player

class DataAdapter(
    private val onPlayerClick: (Player) -> Unit,
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    var Listofplayer: List<Player>? = null
    var onItemClick: ((MainMatchDetail) -> Unit)? = null


    fun setData(Listofplayer: List<Player>) {
        this.Listofplayer = Listofplayer
        notifyDataSetChanged()
    }

    private lateinit var binding: ScoreboardRowBinding

    class ViewHolder(private val binding: ScoreboardRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player?) {
            binding.player = player
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ScoreboardRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val players = Listofplayer?.get(position)
        holder.bind(players)

        holder.itemView.setOnClickListener {
            players?.let {
                onPlayerClick.invoke(
                    players
                )
            }
//            val dialog = BottomSheetDialog(context)
//            val inflater = LayoutInflater.from(context)
//            val view = inflater.inflate(R.layout.fragment_details, null)
//            val btnClose = view.findViewById<Button>(R.id.dismiss)
//            btnClose.setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.setCancelable(true)
//            dialog.setCanceledOnTouchOutside(true)
//            dialog.setContentView(view)
//            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return Listofplayer?.size ?: 0
    }

}