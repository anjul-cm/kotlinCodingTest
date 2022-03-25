package com.codingmountain.kotlincodingtest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingmountain.kotlincodingtest.databinding.ActivityDashBoardBinding
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivity
import com.codingmountain.kotlincodingtest.ui.base.BaseActivity
import com.codingmountain.kotlincodingtest.ui.main.recyclerview.ChargingStationsRVA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardBinding

    private val viewModel: DashBoardActivityViewModel by viewModels()

    private val adapter = ChargingStationsRVA()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setObserverForCurrentUser()
        setUpAdapterForRecyclerView()
        setClickListenerForLogOutBtn()
        setContentView(binding.root)
    }

    private fun setUpAdapterForRecyclerView() {
        binding.dashboardActStationsRV.adapter = adapter
        binding.dashboardActStationsRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        lifecycleScope.launch {
            viewModel.chargingStationFlow.collectLatest {
                adapter.submitData(it)
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

    private fun setClickListenerForLogOutBtn() {
        binding.dashBoardActLogOutBtn.setOnClickListener {
            viewModel.logOut()
        }
    }
}