package com.codingmountain.kotlincodingtest.ui.auth

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.codingmountain.kotlincodingtest.databinding.ActivityAuthBinding
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.ui.auth.login.LoginFragment
import com.codingmountain.kotlincodingtest.ui.base.BaseActivity
import com.codingmountain.kotlincodingtest.ui.main.DashBoardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val viewModel: AuthActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.authActFragmentContainer.id, LoginFragment())
                .commit()
        }

        observeAuthenticationLiveData()
        setContentView(binding.root)
    }

    private fun observeAuthenticationLiveData() {
        viewModel.authenticationLiveData.observe(this) { authenticationLiveData ->
            when (authenticationLiveData) {
                is Resource.Failure -> {
                    hideLoading()
                    showErrorToast(authenticationLiveData.errorMsg)
                }
                Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    startActivity(Intent(this, DashBoardActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun showErrorToast(error: String) {
        Toast.makeText(
            this,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideLoading() {
        binding.authActLoadingProgressBar.visibility = View.GONE
        makeScreenTouchable()
        binding.root.foreground = null
    }

    private fun makeScreenTouchable() {
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun showLoading() {
        binding.authActLoadingProgressBar.visibility = View.VISIBLE
        makeScreenUnTouchable()
        binding.root.foreground = ColorDrawable(126 shl 24)

    }

    private fun makeScreenUnTouchable() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

}