package com.manis.gitapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manis.gitapp.data.dao.GitDao
import com.manis.gitapp.model.GitModel

@Database(version = 1, entities = [GitModel::class], exportSchema = false)
abstract class GitDatabase : RoomDatabase() {
    abstract fun gitDao(): GitDao
}