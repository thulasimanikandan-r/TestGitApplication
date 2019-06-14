package com.manis.gitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manis.gitapp.GitApp
import com.manis.gitapp.data.repository.GitRepository
import com.manis.gitapp.model.GitModel

class GitViewModel(private val gitRepository: GitRepository) : ViewModel() {

    var ownerList: MutableLiveData<MutableList<GitModel>> = MutableLiveData()
    var owner: MutableLiveData<GitModel> = MutableLiveData()


    fun getAllDataUsers(){
        gitRepository.getAllUsersFromApi()
    }

    fun setOwnerList() : LiveData<MutableList<GitModel>>{
        return gitRepository.getAllUserFromDB()
    }



    @Suppress("UNCHECKED_CAST")
    internal class GitViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GitViewModel::class.java)) {
                return GitViewModel(gitRepository = GitRepository(
                        gitDao = GitApp.database.gitDao()
                )) as T
            }
            throw IllegalArgumentException("invalid view model")
        }
    }
}