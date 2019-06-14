package com.manis.gitapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manis.gitapp.model.PullsModel

@Dao
interface PullsDao {

    @Query("Select * from PullsModel where id = :id limit 1")
    fun getData(id: Int): LiveData<PullsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(gitModel: MutableList<PullsModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(gitModel: PullsModel): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(gitModel: PullsModel)

    @Query("Select * from PullsModel")
    fun getAllData(): LiveData<MutableList<PullsModel>>

}