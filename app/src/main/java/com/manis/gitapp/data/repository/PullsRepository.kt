package com.manis.gitapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.manis.gitapp.GitApp
import com.manis.gitapp.data.dao.PullsDao
import com.manis.gitapp.model.PullsModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class PullsRepository(private val pullsDao: PullsDao) {

    fun getAllPullsFromDB(): LiveData<MutableList<PullsModel>> {
        return pullsDao.getAllData()
    }

    fun insertAllData(data: MutableList<PullsModel>) {
        Completable.fromAction {
            pullsDao.insertAllData(data)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun getAllPullsFromApi() {
        val fullName: String? = GitApp.prefs.getString(GitApp.USER_NAME, "")
        val repoName: String? = GitApp.prefs.getString(GitApp.REPO_NAME, "")

        val compositeDisposable = CompositeDisposable()
        val subscribe = GitApp.gitService.getUserPullRequest(fullName!!, repoName!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MutableList<PullsModel>>() {
                    override fun onNext(pullRequests: MutableList<PullsModel>) {

                        val openStatePulls: MutableList<PullsModel> = arrayListOf()
                        for (pullsModel in pullRequests) {

                            if (pullsModel.state.equals(pullRequestState)) {
                                openStatePulls.add(pullsModel)
                            }
                        }
                        Log.d("test", "openRequests->$openStatePulls")
                        insertAllData(openStatePulls)
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

    companion object {
        const val pullRequestState = "open"
    }

}