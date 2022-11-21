package com.example.laboratory5_Fedeiko.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.laboratory5_Fedeiko.model.DataUser
import com.example.laboratory5_Fedeiko.retrofit.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetroRepository):
    ViewModel() {

    fun getAllRepositoryList(): LiveData<List<DataUser>> {
        return repository.getAllRecords()
    }

    // https://reqres.in/api/users?page=1 якщо поставити 2 то дані будуть братися з https://reqres.in/api/users?page=2
    fun makeApiCall() {
        repository.makeApiCall("1")
    }
}