package com.ashok.sportzinteractivedemo.ui.viewmodel

import androidx.lifecycle.*
import com.ashok.sportzinteractivedemo.model.MatchModel
import com.ashok.sportzinteractivedemo.ui.interfaces.RecyclerViewItemClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MatchDetailsViewModel @Inject constructor()
    : ViewModel() {
    private val _matchModel = MutableLiveData<MatchModel>()
    val matchModel: LiveData<MatchModel> get() = _matchModel
    lateinit var receiverMatchData : MatchModel

    private val _itemClickListener = MutableLiveData<RecyclerViewItemClickListener>()


    fun setMatchModel(matchModel: MatchModel?) {
        _matchModel.value = matchModel!!
        receiverMatchData = matchModel
    }

}


