package com.ashok.sportzinteractivedemo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashok.sportzinteractivedemo.R
import com.ashok.sportzinteractivedemo.databinding.TeamsRowAllBinding
import com.ashok.sportzinteractivedemo.databinding.TeamsRowBinding
import com.ashok.sportzinteractivedemo.model.Player
import com.ashok.sportzinteractivedemo.ui.interfaces.RecyclerViewItemClickListener

internal class MatchSingleTeamDetailsAdapter(private val items: Map<String, Player>?,
                                             private val itemClickListener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<MatchSingleTeamDetailsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.teams_row_all, parent, false)

        val inflater = LayoutInflater.from(parent.context)
        val binding = TeamsRowBinding.inflate(inflater, parent, false)


        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {


        val list = items!!.entries.toList()
        val entry = list[position]
        val key = entry.key
        val value = entry.value


        holder.bind(key, value,itemClickListener)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    class ItemViewHolder(private val binding: TeamsRowBinding,) : RecyclerView.ViewHolder(binding.root) {
        fun bind(key : String,data: Player,itemClickListener: RecyclerViewItemClickListener) {

            val appendText = when {
                data.isCaptain && data.isKeeper -> {
                    " (c & w)"
                }
                data.isCaptain -> {
                    " (c)"
                }
                data.isKeeper -> {
                    " (w)"
                }
                else -> ""
            }

            binding.txtPlayerName.text = data.nameFull+appendText
           binding.txtPlayerType.text = data.batting.style

            itemView.setOnClickListener{
                itemClickListener.onItemClicked(data)

            }
        }

    }
}