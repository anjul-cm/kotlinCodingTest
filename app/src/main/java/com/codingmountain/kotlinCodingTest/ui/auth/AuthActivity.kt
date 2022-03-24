package com.codingmountain.kotlincodingtest.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingmountain.kotlincodingtest.databinding.ActivityAuthBinding
import com.codingmountain.kotlincodingtest.ui.auth.login.LoginFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.authActivityContainer.id, LoginFragment())
                .commit()
        }

        setContentView(binding.root)
    }

}