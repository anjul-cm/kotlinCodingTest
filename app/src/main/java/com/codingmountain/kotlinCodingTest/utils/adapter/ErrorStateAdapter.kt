/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/26/22, 2:35 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingmountain.kotlincodingtest.databinding.LayoutNoDataFoundBinding

class ErrorStateAdapter : RecyclerView.Adapter<ErrorStateAdapter.Holder>() {
    class Holder(binding: LayoutNoDataFoundBinding) : RecyclerView.ViewHolder(binding.root)

    var isDisplayed = false
        set(isDisplayed) {
            if (field != isDisplayed) {
                if (isDisplayed) {
                    notifyItemInserted(0)
                } else {
                    notifyItemRemoved(0)
                }
                field = isDisplayed
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutNoDataFoundBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    override fun getItemCount(): Int = if (isDisplayed) 1 else 0

}
