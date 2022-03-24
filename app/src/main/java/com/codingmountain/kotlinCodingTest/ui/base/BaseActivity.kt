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