package com.example.laboratory5_Fedeiko.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.laboratory5_Fedeiko.model.DataUser
import com.example.laboratory5_Fedeiko.model.TypeConverterDataUser

@Database(entities = [DataUser::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterDataUser::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getAppDao(): UserDao

    companion object {
        private var DB_INSTANCE: UserDatabase? = null

        fun getAppDBInstance(context: Context): UserDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "DB-Lab5"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            Log.d("log", "DB created")
            return DB_INSTANCE!!
        }
    }
}