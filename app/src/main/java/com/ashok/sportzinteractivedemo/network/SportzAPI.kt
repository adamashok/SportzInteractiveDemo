package com.ashok.sportzinteractivedemo.network

import com.ashok.sportzinteractivedemo.model.MatchModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SportzAPI {






    @GET("sapk01222019186652.json.json")
     fun getMatchDetails() : Call<ResponseBody>

     @GET("nzin01312019187360.json")
     suspend fun getMatchDetailsNew() : Response<MatchModel>


}