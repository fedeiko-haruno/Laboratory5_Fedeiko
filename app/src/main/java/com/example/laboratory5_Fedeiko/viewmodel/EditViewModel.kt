package com.example.laboratory5_Fedeiko.viewmodel

import androidx.lifecycle.ViewModel
import com.example.laboratory5_Fedeiko.db.RoomRepository
import com.example.laboratory5_Fedeiko.model.DataUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val repository: RoomRepository):
    ViewModel() {

    fun getDataUserById(id: Int): DataUser {
        return repository.getDataUserById(id)
    }

    fun updateRecord(dataUser: DataUser) {
        repository.updateRecord(dataUser)
    }

    fun deleteRecord(dataUser: DataUser) {
        repository.deleteRecord(dataUser)
    }
}