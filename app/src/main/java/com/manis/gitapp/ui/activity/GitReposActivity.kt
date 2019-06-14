package com.manis.gitapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.manis.gitapp.GitApp
import com.manis.gitapp.GitApp.Companion.REPO_NAME
import com.manis.gitapp.GitApp.Companion.USER_FULL_NAME
import com.manis.gitapp.GitApp.Companion.USER_NAME
import com.manis.gitapp.R
import com.manis.gitapp.model.ReposModel
import com.manis.gitapp.ui.adapter.ReposAdapter
import com.manis.gitapp.ui.viewmodel.ReposViewModel
import com.manis.gitapp.utils.navigateToActivity
import kotlinx.android.synthetic.main.activity_main.*

class GitReposActivity : AppCompatActivity() {

    lateinit var viewModel: ReposViewModel
    var bundle:Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_repos)
        title = getString(R.string.title_repos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, ReposViewModel.ReposViewModelFactory())
                .get(ReposViewModel::class.java)

        val userName: String? = GitApp.prefs.getString(USER_NAME, "")
        setupReposData(userName!!)

        refreshOwnerData.setOnRefreshListener {
            setupReposData(userName)
        }
    }

    private fun setupReposData(userName: String) {
        viewModel.getUserRepos(userName)
        viewModel.setReposList().observe(this, Observer {
            setRecyclerView(it)
        })
        refreshOwnerData.isRefreshing = false
    }

    private fun setRecyclerView(ownerList: MutableList<ReposModel>) {
        if (!ownerList.isNullOrEmpty()) {
            ownerRecyclerView.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE

            val gitAdapter = ReposAdapter(ownerList)
            ownerRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@GitReposActivity)
                adapter = gitAdapter
                gitAdapter.onItemClick = {
                    Log.d("test", "test->$it")
                    GitApp.prefs.edit().putString(USER_FULL_NAME, it.full_name).apply()
                    GitApp.prefs.edit().putString(REPO_NAME, it.name).apply()
                    navigateToActivity(this@GitReposActivity, OpenPullsActivity::class.java, null)
                }
            }
        } else {
            ownerRecyclerView.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
        }
    }
}