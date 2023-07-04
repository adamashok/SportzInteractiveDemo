package com.ashok.sportzinteractivedemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashok.sportzinteractivedemo.databinding.TeamsRowAllBinding
import com.ashok.sportzinteractivedemo.model.Player
import com.ashok.sportzinteractivedemo.ui.interfaces.RecyclerViewItemClickListener

class MatchAllDetailsAdapter(private val itemsTeamA: Map<String, Player>?
                             , private val itemsTeamB: Map<String, Player>?,
                             private val itemClickListener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<MatchAllDetailsAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = TeamsRowAllBinding.inflate(inflater, parent, false)


        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {


        val listTeamA = itemsTeamA!!.entries.toList()
        val entry = listTeamA[position]
        val keyTeamA = entry.key
        val valueTeamA = entry.value

        val listTeamB = itemsTeamB!!.entries.toList()
        val entryTeamB = listTeamB[position]
        val keyTeamB = entryTeamB.key
        val valueTeamB = entryTeamB.value


        holder.bind(keyTeamA,valueTeamA,keyTeamB, valueTeamB,itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemsTeamA!!.size
    }

    class ItemViewHolder(private val binding: TeamsRowAllBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(teamAKey : String,teamAData : Player,teamBKey : String, teamBData: Player, itemClickListener: RecyclerViewItemClickListener) {

        val appendTextTeamA = when {
            teamAData.isCaptain && teamAData.isKeeper -> {
                " (c & w)"
            }
            teamAData.isCaptain -> {
                " (c)"
            }
            teamAData.isKeeper -> {
                " (w)"
            }
            else -> ""
        }

        val appendTextTeamB = when {
            teamBData.isCaptain && teamBData.isKeeper -> {
                " (c & w)"
            }
            teamBData.isCaptain -> {
                " (c)"
            }
            teamBData.isKeeper -> {
                " (w)"
            }
            else -> ""
        }


            binding.txtPlayerNameTeamA.text = teamAData.nameFull + appendTextTeamA
            binding.txtPlayerTypeTeamA.text = teamAData.batting.style

            binding.txtPlayerNameTeamB.text = teamBData.nameFull + appendTextTeamB
            binding.txtPlayerTypeTeamB.text = teamBData.batting.style

            binding.constraintLayoutTeamA.setOnClickListener{
                itemClickListener.onItemClicked(teamAData)

            }

            binding.constraintLayoutTeamB.setOnClickListener{
                itemClickListener.onItemClicked(teamBData)

            }
        }



    }


}