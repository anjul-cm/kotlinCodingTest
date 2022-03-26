/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 8:45 AM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivity
import com.codingmountain.kotlincodingtest.ui.main.dashboard.DashBoardActivity
import com.google.firebase.auth.FirebaseAuth

class StartingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startRequiredActivity()
    }

    private fun startRequiredActivity() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivityFinishingCurrentOne(AuthActivity::class.java)
        } else {
            startActivityFinishingCurrentOne(DashBoardActivity::class.java)
        }
    }

    private fun <T> startActivityFinishingCurrentOne(activity: Class<T>) {
        startActivity(Intent(this, activity))
        finish()
    }


}