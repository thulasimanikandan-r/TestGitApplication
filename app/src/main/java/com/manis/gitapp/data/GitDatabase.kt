package com.manis.gitapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manis.gitapp.data.dao.GitDao
import com.manis.gitapp.data.dao.PullsDao
import com.manis.gitapp.data.dao.RepoDao
import com.manis.gitapp.model.GitModel
import com.manis.gitapp.model.PullsModel
import com.manis.gitapp.model.ReposModel

@Database(version = 3, entities = [GitModel::class, ReposModel::class, PullsModel::class], exportSchema = false)
abstract class GitDatabase : RoomDatabase() {
    abstract fun gitDao(): GitDao
    abstract  fun reposDao() : RepoDao
    abstract  fun pullsDao() : PullsDao
}