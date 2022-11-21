package com.example.laboratory5_Fedeiko.data_url

import android.app.Application
import com.example.laboratory5_Fedeiko.db.UserDao
import com.example.laboratory5_Fedeiko.db.UserDatabase
import com.example.laboratory5_Fedeiko.retrofit.RetroInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): UserDatabase {
        return UserDatabase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.getAppDao()
    }

    // найшла в інтернеті даний сайт з відкритим json файлом
    val BASE_URL = "https://reqres.in/api/"

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): RetroInterface {
        return retrofit.create(RetroInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}