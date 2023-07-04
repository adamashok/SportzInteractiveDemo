package com.ashok.sportzinteractivedemo.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.ashok.sportzinteractivedemo.R
import com.ashok.sportzinteractivedemo.databinding.FragmentHomeBinding
import com.ashok.sportzinteractivedemo.model.MatchModel
import com.ashok.sportzinteractivedemo.ui.activity.MatchDetailsActivity
import com.ashok.sportzinteractivedemo.ui.viewmodel.HomeViewModel
import com.ashok.sportzinteractivedemo.utils.CommonUtils
import com.ashok.sportzinteractivedemo.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    lateinit var matchDataModel : MatchModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(CommonUtils.hasInternetConnection(requireActivity())){
            homeViewModel.getMatchDetails()

        }
        else{

            // handle the no internet error
            Toast.makeText(requireContext(),"No Internet Connection",Toast.LENGTH_SHORT)
        }
        observeViewModels()

        binding.lnrRoot.setOnClickListener {
            val intent = Intent(requireActivity(),MatchDetailsActivity::class.java)
            intent.putExtra("matchData", matchDataModel)
            startActivity(intent)


        }
    }

    private fun observeViewModels() {

        homeViewModel.matchResponseModel.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {

                    // hideShowProgress
                    it.data?.let {
                        matchDataModel = it
                        setUI(it)
                    }

                }
                Status.LOADING -> {
//                     showShowProgress
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    fun setUI(matchData: MatchModel){

        binding.txtTournamentName.text = matchData.matchDetail.series.tourName


        val homeTeamKey = matchData.matchDetail.teamHome
        val awayTeamKey = matchData.matchDetail.teamAway

        binding.txtFirstTeamName.text = matchData.teams.get(homeTeamKey)?.nameShort
        binding.txtSecondTeamName.text = matchData.teams.get(awayTeamKey)?.nameShort


        binding.txtDate.text = matchData.matchDetail.match.date
        binding.txtVenue.text = matchData.matchDetail.venue.name

    }
}