package com.manis.gitapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manis.gitapp.model.GitModel

@Dao
interface GitDao {

    @Query("Select * from GitModel where id = :id limit 1")
    fun getData(id: Long): LiveData<GitModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(gitModel: MutableList<GitModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(gitModel: GitModel): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(gitModel: GitModel)

    @Query("Select * from GitModel")
    fun getAllData(): LiveData<MutableList<GitModel>>
}