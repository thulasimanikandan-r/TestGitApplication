package com.manis.gitapp.data.api

import com.manis.gitapp.model.GitModel
import com.manis.gitapp.model.PullsModel
import com.manis.gitapp.model.ReposModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitServiceApi{

    @GET("users/{user}")
    fun getUser(@Path("user") user: String) : Call<GitModel>

    @GET("users")
    fun getAllUsers() : Observable<MutableList<GitModel>>

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String) : Observable<MutableList<ReposModel>>

    @GET("repos/{fullName}/pulls")
    fun getUserPullRequest(@Path("fullName") fullName : String) : Observable<MutableList<PullsModel>>

    @GET("repos/{user}/{fullName}/pulls")
    fun getUserPullRequest(@Path("user") user: String,@Path("fullName") fullName : String) : Observable<MutableList<PullsModel>>

    @GET("repos/defunkt/exception_logger/pulls")
    fun getUserPullRequest() : Observable<MutableList<PullsModel>>
}