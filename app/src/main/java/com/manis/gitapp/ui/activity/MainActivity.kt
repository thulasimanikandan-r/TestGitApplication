package com.manis.gitapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.manis.gitapp.R
import com.manis.gitapp.model.GitModel
import com.manis.gitapp.ui.adapter.GitAdapter
import com.manis.gitapp.ui.viewmodel.GitViewModel
import com.manis.gitapp.utils.navigateToActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title_owners)

        viewModel = ViewModelProviders.of(this, GitViewModel.GitViewModelFactory())
                .get(GitViewModel::class.java)

        setupOwnerData()
        refreshOwnerData.setOnRefreshListener {
            setupOwnerData()
        }
    }

    private fun setupOwnerData() {
        viewModel.getAllDataUsers()
        viewModel.setOwnerList().observe(this, Observer {
            setRecyclerView(it)
        })
        refreshOwnerData.isRefreshing = false
    }

    private fun setRecyclerView(ownerList: MutableList<GitModel>) {
        if (!ownerList.isNullOrEmpty()) {
            ownerRecyclerView.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE

            val gitAdapter = GitAdapter(ownerList)
            ownerRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = gitAdapter
                gitAdapter.onItemClick = {
                    val bundle = Bundle()
                    bundle.putLong("userId", it.id)
                    bundle.putString("userName", it.login)
                    navigateToActivity(this@MainActivity, GitReposActivity::class.java, bundle)
                }
            }
        } else {
            ownerRecyclerView.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
        }
    }
}
