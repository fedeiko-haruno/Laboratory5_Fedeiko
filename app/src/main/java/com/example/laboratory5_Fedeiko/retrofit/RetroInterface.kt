package com.example.laboratory5_Fedeiko.retrofit

import com.example.laboratory5_Fedeiko.model.UsersList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroInterface {

    @GET("users")
    fun getDataFromAPI(@Query("page") query: String): Call<UsersList>
}