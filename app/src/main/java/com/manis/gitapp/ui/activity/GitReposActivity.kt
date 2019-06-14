package com.manis.gitapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.manis.gitapp.R
import com.manis.gitapp.model.ReposModel
import com.manis.gitapp.ui.adapter.ReposAdapter
import com.manis.gitapp.ui.viewmodel.ReposViewModel
import kotlinx.android.synthetic.main.activity_main.*

class GitReposActivity : AppCompatActivity() {

    lateinit var viewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_repos)
        title = getString(R.string.title_repos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, ReposViewModel.ReposViewModelFactory())
                .get(ReposViewModel::class.java)

        val bundle = intent.extras
        var userName: String? = null
        if (bundle != null) {
            userName = bundle.getString("userName", "")
        }
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
                    //  navigateToActivity(this@GitReposActivity, GitReposActivity::class.java, null)
                }
            }
        } else {
            ownerRecyclerView.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
        }
    }
}