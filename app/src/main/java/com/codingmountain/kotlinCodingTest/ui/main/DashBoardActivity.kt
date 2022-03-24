package com.codingmountain.kotlincodingtest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingmountain.kotlincodingtest.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}