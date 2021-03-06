/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/24/22, 10:27 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (isTaskRoot && supportFragmentManager.backStackEntryCount == 0) {
            showDialogAskingConfirmationToExitApp()
        } else {
            super.onBackPressed()
        }
    }

    private fun showDialogAskingConfirmationToExitApp() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

}