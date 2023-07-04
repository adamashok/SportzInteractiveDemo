package com.ashok.sportzinteractivedemo.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ashok.sportzinteractivedemo.databinding.ActivityMatchDetailsBinding
import com.ashok.sportzinteractivedemo.model.MatchModel
import com.ashok.sportzinteractivedemo.model.Player
import com.ashok.sportzinteractivedemo.ui.adapters.MatchAllDetailsAdapter
import com.ashok.sportzinteractivedemo.ui.adapters.MatchSingleTeamDetailsAdapter
import com.ashok.sportzinteractivedemo.ui.fragments.CustomBottomSheetDialogFragment
import com.ashok.sportzinteractivedemo.ui.interfaces.RecyclerViewItemClickListener
import com.ashok.sportzinteractivedemo.ui.viewmodel.MatchDetailsViewModel
import com.ashok.sportzinteractivedemo.utils.LayoutManagerUtil.getVerticalLayoutManager
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailsActivity : AppCompatActivity(), RecyclerViewItemClickListener {

    private lateinit var binding: ActivityMatchDetailsBinding

    lateinit var receiverMatchData : MatchModel
    private val matchDetailsViewModel: MatchDetailsViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMatchDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        receiverMatchData = (intent.getSerializableExtra("matchData") as? MatchModel)!!
        matchDetailsViewModel.setMatchModel(receiverMatchData)


        setSupportActionBar(binding.toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);


        binding.teamAOption.text = receiverMatchData.teams.get(receiverMatchData.matchDetail.teamHome)?.nameFull
        binding.teamBOption.text = receiverMatchData.teams.get(receiverMatchData.matchDetail.teamAway)?.nameFull

        matchDetailsViewModel.matchModel.observe(this) { matchModel ->

            // future data if data comes from API

        }

        binding.allOption.setOnClickListener {
            setupAllRecyclerViews()
            showAllTeams()
        }
        binding.teamAOption.setOnClickListener {
            toggleTeamState("TeamA")
        }
        binding.teamBOption.setOnClickListener {
            toggleTeamState("TeamB")
        }

        setupAllRecyclerViews()
        binding.recyclerSingleTeam.visibility = View.GONE



    }

    private fun setupAllRecyclerViews() {


        val teamAData =  receiverMatchData.teams.get(receiverMatchData.matchDetail.teamHome)?.players
        val teamBData =  receiverMatchData.teams.get(receiverMatchData.matchDetail.teamAway)?.players


        binding.apply {


            val adapterAllTeams = MatchAllDetailsAdapter(teamAData,teamBData,this@MatchDetailsActivity)
            recyclerAllTeams.layoutManager = getVerticalLayoutManager(this@MatchDetailsActivity)
            recyclerAllTeams.adapter = adapterAllTeams



        }

    }

    private fun toggleTeamState(team: String) {

        var teamData : Map<String, Player>?

        if(team == "TeamA"){

             teamData =  receiverMatchData.teams.get(receiverMatchData.matchDetail.teamHome)?.players

        }
        else{
             teamData =  receiverMatchData.teams.get(receiverMatchData.matchDetail.teamAway)?.players

        }

        val adapterSingleTeam = MatchSingleTeamDetailsAdapter(teamData,this@MatchDetailsActivity)
        binding.recyclerSingleTeam.layoutManager = getVerticalLayoutManager(this@MatchDetailsActivity)
        binding.recyclerSingleTeam.adapter = adapterSingleTeam

        showSingleTeam()

    }

    fun showAllTeams(){
        binding.recyclerSingleTeam.visibility = View.GONE
        binding.recyclerAllTeams.visibility = View.VISIBLE
    }

    fun showSingleTeam(){
        binding.recyclerSingleTeam.visibility = View.VISIBLE
        binding.recyclerAllTeams.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClicked(item: Player) {
        val bottomSheetFragment = CustomBottomSheetDialogFragment.newInstance(item)
        bottomSheetFragment.show(supportFragmentManager, "ModalBottomSheet")
    }

}