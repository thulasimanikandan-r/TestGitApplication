package com.manis.gitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manis.gitapp.GitApp
import com.manis.gitapp.data.repository.PullsRepository
import com.manis.gitapp.model.PullsModel

class PullsViewModel(private val pullsRepository: PullsRepository) : ViewModel() {

    fun getUserPulls(){
        pullsRepository.getAllPullsFromApi()
    }

    fun setPullsList() : LiveData<MutableList<PullsModel>>{
        return pullsRepository.getAllPullsFromDB()
    }

    @Suppress("UNCHECKED_CAST")
    internal class PullsViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PullsViewModel::class.java)) {
                return PullsViewModel(pullsRepository = PullsRepository(
                        pullsDao = GitApp.database.pullsDao()
                )) as T
            }
            throw IllegalArgumentException("invalid view model")
        }
    }
}