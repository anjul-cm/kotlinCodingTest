package com.codingmountain.kotlincodingtest.ui.main.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingmountain.kotlincodingtest.R
import com.codingmountain.kotlincodingtest.databinding.ActivityDashBoardBinding
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivity
import com.codingmountain.kotlincodingtest.ui.base.BaseActivity
import com.codingmountain.kotlincodingtest.ui.main.dashboard.filterhelper.FilterHelper
import com.codingmountain.kotlincodingtest.ui.main.dashboard.recyclerview.ChargingStationsRVA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardBinding

    private lateinit var filterHelper: FilterHelper
    private val viewModel: DashBoardActivityViewModel by viewModels()


    private var searchQuery = ""
    private val adapter = ChargingStationsRVA()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)

        setUpToolbar()
        initializeFilterHelper()

        setObserverForCurrentUser()
        setUpSearchQueryChangeListener()
        setUpObserverForFetchingData()

        setUpAdapterForRecyclerView()

        setContentView(binding.root)
    }

    private fun setUpObserverForFetchingData() {
        viewModel.fetchChargingLiveData.observe(this) { fetchingStatus ->
            binding.dashboardActFetchingProgressBar.visibility = when (fetchingStatus) {
                is Resource.Failure -> View.INVISIBLE
                Resource.Loading -> View.VISIBLE
                is Resource.Success -> View.INVISIBLE
            }
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.dashboardActToolBar)
        binding.dashboardActToolBar.title = ""
        binding.dashboardActToolBar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_overflow)
    }

    private fun setUpSearchQueryChangeListener() {
        binding.dashboardActStationSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchQuery = newText
                adapter.refresh()
                return true
            }
        })
    }

    private fun initializeFilterHelper() {
        filterHelper = FilterHelper(
            this,
            binding.dashBoardActFilterContainingScrollView,
            adapter,
            supportFragmentManager
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard_act, menu)
        setUpObserverForDataSourceLiveData(menu.findItem(R.id.dashBoardMenu_changeDataSource))
        return true
    }

    private fun setUpObserverForDataSourceLiveData(menuItem: MenuItem?) {
        menuItem?.let {
            viewModel.hardCodedDataSourceLiveData.observe(this) { hardCoded ->
                menuItem.title = if (hardCoded) {
                    binding.dashboardActToolBar.title = "Hard Coded Data"
                    "Show Local Data"
                } else {
                    binding.dashboardActToolBar.title = "Locally Stored Data"
                    "Show Hard Coded Data"
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dashBoardMenu_filter -> {
                filterHelper.showAddFilterFragment()
            }
            R.id.dashBoardMenu_deleteLocal -> {
                viewModel.deleteAllData()
            }
            R.id.dashBoardMenu_fetchData -> {
                viewModel.fetchChargingStation()
            }
            R.id.dashBoardMenu_logOut -> {
                viewModel.logOut()
            }
            R.id.dashBoardMenu_changeDataSource -> {
                viewModel.changeDataSource()
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true

    }

    private fun setUpAdapterForRecyclerView() {
        binding.dashboardActStationsRV.adapter = adapter
        binding.dashboardActStationsRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.chargingStationLiveData.observe(this) {
            lifecycleScope.launch {
                adapter.submitData(
                    filterHelper.filterData(it, searchQuery)
                )
            }
        }

    }

    private fun setObserverForCurrentUser() {
        viewModel.loggedInUserLiveData.observe(this) {
            if (it == null) {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }
    }
}