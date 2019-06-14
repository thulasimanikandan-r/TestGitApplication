package com.manis.gitapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.manis.gitapp.GitApp
import com.manis.gitapp.data.dao.GitDao
import com.manis.gitapp.model.GitModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class GitRepository(val gitDao: GitDao) {

    fun getAllUserFromDB(): LiveData<MutableList<GitModel>> {
        return gitDao.getAllData()
    }

    fun getUserFromDB(id:Int): LiveData<GitModel> {
        return gitDao.getData(id)
    }

    fun insertAllData(data: MutableList<GitModel>) {
        Completable.fromAction {
            gitDao.insertAllData(data)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun getAllUsersFromApi() {
        val compositeDisposable = CompositeDisposable()
        val subscribe = GitApp.gitService.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MutableList<GitModel>>() {
                    override fun onNext(t: MutableList<GitModel>) {
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