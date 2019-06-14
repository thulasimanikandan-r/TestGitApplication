package com.manis.gitapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.manis.gitapp.GitApp
import com.manis.gitapp.data.dao.RepoDao
import com.manis.gitapp.model.ReposModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ReposRepository(val repoDao: RepoDao) {


    fun getAllReposFromDB(): LiveData<MutableList<ReposModel>> {
        return repoDao.getAllData()
    }

    fun insertAllData(data: MutableList<ReposModel>) {
        Completable.fromAction {
            repoDao.insertAllData(data)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun getAllUsersFromApi(username : String) {
        val compositeDisposable = CompositeDisposable()
        val subscribe = GitApp.gitService.getUserRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MutableList<ReposModel>>() {
                    override fun onNext(t: MutableList<ReposModel>) {
                        insertAllData(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("test", "e->${e.message}")
                    }

                    override fun onComplete() {
                        Log.d("test", "completed")
                    }

                })
        compositeDisposable.add(subscribe)
    }

}