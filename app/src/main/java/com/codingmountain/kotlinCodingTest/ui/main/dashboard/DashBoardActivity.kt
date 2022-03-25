package com.codingmountain.kotlincodingtest.ui.main.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingmountain.kotlincodingtest.R
import com.codingmountain.kotlincodingtest.databinding.ActivityDashBoardBinding
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivity
import com.codingmountain.kotlincodingtest.ui.base.BaseActivity
import com.codingmountain.kotlincodingtest.ui.main.dashboard.filterhelper.FilterHelper
import com.codingmountain.kotlincodingtest.ui.main.dashboard.recyclerview.ChargingStationsRVA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        setUpAdapterForRecyclerView()

        viewModel.fetchChargingStation()
        setContentView(binding.root)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.dashboardActToolBar)
        binding.dashboardActToolBar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_overflow)
    }

    private fun setUpSearchQueryChangeListener() {
        binding.dashboardActStationSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery = newText.toString()
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dashBoardMenu_filter -> {
                filterHelper.showAddFilterFragment()
            }
            R.id.dashBoardMenu_deleteLocal -> {
                viewModel.deleteAllData()
            }
            R.id.dashBoardMenu_logOut -> {
                viewModel.logOut()
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


        lifecycleScope.launch {
            viewModel.chargingStationFlow.collectLatest {
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