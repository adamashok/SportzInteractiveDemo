package com.ashok.sportzinteractivedemo.ui.viewmodel

import androidx.lifecycle.*
import com.ashok.sportzinteractivedemo.model.MatchModel
import com.ashok.sportzinteractivedemo.network.SportzAPI
import com.ashok.sportzinteractivedemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val yelpAPI: SportzAPI)
    : ViewModel() {
//    var matchResponseModel = MutableLiveData<Resource<MatchDetailsModel>>()
    var matchResponseModel = MutableLiveData<Resource<MatchModel>>()
    lateinit var matchData  : MatchModel


    fun getMatchDetails() {

        viewModelScope.launch {
            matchResponseModel.postValue(Resource.loading(null))

            try {
                val response  = yelpAPI.getMatchDetailsNew()
                val responseBody : MatchModel = response.body()!!
                matchResponseModel.postValue(Resource.success(responseBody))
                matchData = responseBody
            }catch (e:Exception){
                e.printStackTrace()
            }

        }


    }

    fun getTeamAndPlayersName(teamKey: String ){

        val teamsMap = matchData.teams.get(teamKey)?.nameShort

    }

}


