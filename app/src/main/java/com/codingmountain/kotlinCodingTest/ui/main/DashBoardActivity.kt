package com.codingmountain.kotlincodingtest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.codingmountain.kotlincodingtest.databinding.ActivityDashBoardBinding
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivity
import com.codingmountain.kotlincodingtest.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardBinding

    private val viewModel: DashBoardActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setObserverForCurrentUser()
        setClickListenerForLogOutBtn()
        setContentView(binding.root)
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