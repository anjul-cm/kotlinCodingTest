package com.codingmountain.kotlincodingtest.ui.main

import android.os.Bundle
import com.codingmountain.kotlincodingtest.databinding.ActivityDashBoardBinding
import com.codingmountain.kotlincodingtest.ui.base.BaseActivity

class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}