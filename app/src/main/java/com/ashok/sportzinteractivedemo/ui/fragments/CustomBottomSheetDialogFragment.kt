package com.ashok.sportzinteractivedemo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ashok.sportzinteractivedemo.R
import com.ashok.sportzinteractivedemo.databinding.BottomsheetPlayerinfoBinding
import com.ashok.sportzinteractivedemo.databinding.FragmentHomeBinding
import com.ashok.sportzinteractivedemo.model.Player
import com.ashok.sportzinteractivedemo.ui.activity.MatchDetailsActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {


    private var _binding: BottomsheetPlayerinfoBinding? = null
    private val binding: BottomsheetPlayerinfoBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BottomsheetPlayerinfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerInfo = arguments?.getSerializable("playerInfo") as? Player

        if (playerInfo != null) {
            // Set the data to the text views
            binding.textViewName.text = "Name: ${playerInfo.nameFull}"
            binding.textViewTotalRuns.text = "Total Runs: ${playerInfo.batting.runs}"
            binding.textViewBattingAverage.text = "Batting Average: ${playerInfo.batting.average}"
            binding.textViewBowlingAverage.text = "Bowling Average: ${playerInfo.bowling.average}"
            binding.textViewEconomy.text = "Economy: ${playerInfo.bowling.economyRate}"
            binding.textViewWickets.text = "Wickets: ${playerInfo.bowling.wickets}"
        }

    }
    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"
        fun newInstance(player: Player): CustomBottomSheetDialogFragment {
            val fragment = CustomBottomSheetDialogFragment()
            val args = Bundle()
            args.putSerializable("playerInfo", player)
            fragment.arguments = args
            return fragment
        }
    }

}