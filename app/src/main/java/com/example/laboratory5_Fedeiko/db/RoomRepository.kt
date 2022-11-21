package com.example.laboratory5_Fedeiko.db

import com.example.laboratory5_Fedeiko.model.DataUser
import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDao: UserDao) {

    fun insertRecords(dataUser: DataUser) {
        appDao.insertRecords(dataUser)
    }

    fun getDataUserById(id: Int): DataUser {
        return appDao.getDataUserById(id)
    }

    fun updateRecord(dataUser: DataUser) {
        appDao.updateRecord(dataUser)
    }

    fun deleteRecord(dataUser: DataUser) {
        appDao.deleteRecord(dataUser)
    }
}