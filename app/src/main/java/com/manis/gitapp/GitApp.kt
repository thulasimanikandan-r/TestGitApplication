package com.manis.gitapp

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.manis.gitapp.data.GitDatabase
import com.manis.gitapp.data.api.GitServiceApi
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitApp : Application(){

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, GitDatabase::class.java, "git_db_1.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        gitService = retrofit.create(GitServiceApi::class.java)

        prefs =  applicationContext.getSharedPreferences(USER_DETAILS, 0)
    }

    companion object {
        lateinit var database: GitDatabase
        lateinit var retrofit: Retrofit
        lateinit var gitService: GitServiceApi
        lateinit var prefs: SharedPreferences

        const val USER_DETAILS = "user_details"
        const val USER_NAME = "user_name"
        const val REPO_NAME = "repo_name"
        const val USER_FULL_NAME = "user_full_name"
    }
}