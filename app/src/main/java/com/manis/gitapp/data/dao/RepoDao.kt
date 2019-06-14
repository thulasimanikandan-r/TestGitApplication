package com.manis.gitapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manis.gitapp.model.ReposModel

@Dao
interface RepoDao {

    @Query("Select * from ReposModel where id = :id limit 1")
    fun getData(id: Int): LiveData<ReposModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(gitModel: MutableList<ReposModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(gitModel: ReposModel): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(gitModel: ReposModel)

    @Query("Select * from ReposModel")
    fun getAllData(): LiveData<MutableList<ReposModel>>
    
}