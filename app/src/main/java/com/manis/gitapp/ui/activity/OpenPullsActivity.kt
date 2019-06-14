package com.manis.gitapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.manis.gitapp.R
import com.manis.gitapp.model.PullsModel
import com.manis.gitapp.ui.adapter.PullsAdapter
import com.manis.gitapp.ui.viewmodel.PullsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class OpenPullsActivity : AppCompatActivity() {

    lateinit var viewModel: PullsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_repos)
        title = getString(R.string.title_pulls)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, PullsViewModel.PullsViewModelFactory())
                .get(PullsViewModel::class.java)


        setupPullsData()

        refreshOwnerData.setOnRefreshListener {
            setupPullsData()
        }
    }

    private fun setupPullsData() {
        viewModel.getUserPulls()
        viewModel.setPullsList().observe(this, Observer {
            setRecyclerView(it)
        })
        refreshOwnerData.isRefreshing = false
    }

    private fun setRecyclerView(ownerList: MutableList<PullsModel>) {
        if (!ownerList.isNullOrEmpty()) {
            ownerRecyclerView.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE

            val gitAdapter = PullsAdapter(ownerList)
            ownerRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@OpenPullsActivity)
                adapter = gitAdapter
                gitAdapter.onItemClick = {
                    // navigateToActivity(this@, GitReposActivity::class.java, null)
                }
            }
        } else {
            ownerRecyclerView.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
        }
    }
}