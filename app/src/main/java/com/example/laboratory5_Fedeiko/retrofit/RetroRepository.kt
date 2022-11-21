package com.example.laboratory5_Fedeiko.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.laboratory5_Fedeiko.db.UserDao
import com.example.laboratory5_Fedeiko.model.DataUser
import com.example.laboratory5_Fedeiko.model.UsersList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(
    private val retroServiceInterface: RetroInterface,
    private val appDao: UserDao
) {

    fun getAllRecords(): LiveData<List<DataUser>> {
        Log.d("log", "get all records from LiveData")
        return appDao.getAllRecords()
    }

    fun insertRecord(dataUser: DataUser) {
        Log.d("log", "insert record to DB")
        appDao.insertRecords(dataUser)
    }


    fun makeApiCall(query: String?) {
        val call: Call<UsersList> = retroServiceInterface.getDataFromAPI(query!!)
        call.enqueue(object : Callback<UsersList> {
            override fun onResponse(
                call: Call<UsersList>,
                response: Response<UsersList>
            ) {
                if (response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.data?.forEach {
                        insertRecord(it)
                    }
                    Log.d("log", "Success")
                }
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                Log.d("log", "Error")
            }
        })
    }
}