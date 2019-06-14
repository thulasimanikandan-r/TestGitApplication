package com.manis.gitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manis.gitapp.GitApp
import com.manis.gitapp.data.repository.ReposRepository
import com.manis.gitapp.model.ReposModel

class ReposViewModel(private val reposRepository: ReposRepository) : ViewModel() {

    fun getUserRepos(userName : String){
        reposRepository.getAllUsersFromApi(userName)
    }

    fun setReposList() : LiveData<MutableList<ReposModel>>{
        return reposRepository.getAllReposFromDB()
    }

    @Suppress("UNCHECKED_CAST")
    internal class ReposViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
                return ReposViewModel(reposRepository = ReposRepository(
                        repoDao = GitApp.database.reposDao()
                )) as T
            }
            throw IllegalArgumentException("invalid view model")
        }
    }
}