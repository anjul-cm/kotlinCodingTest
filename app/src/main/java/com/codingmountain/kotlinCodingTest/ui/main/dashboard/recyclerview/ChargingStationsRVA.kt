/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/26/22, 8:00 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.main.dashboard.recyclerview

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codingmountain.kotlincodingtest.databinding.LayoutStationBinding
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation

class ChargingStationsRVA :
    PagingDataAdapter<ChargingStation, ChargingStationsRVA.Holder>(ChargingStationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutStationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position)?.let { station ->
            holder.bindView(station)
        }
    }

    inner class Holder(private val binding: LayoutStationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            setClickListenerForPhoneNumTextView()
        }

        private fun setClickListenerForPhoneNumTextView() {
            binding.stationLytPhoneNumTV.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    binding.root.context.startActivity(
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${it.telephone}")
                        }
                    )
                }
            }
        }

        fun bindView(station: ChargingStation) {
            binding.apply {
                stationLytNameTV.text = station.name
                stationLytAddressTV.text = station.address
                stationLytCityTV.text = station.city
                stationLytPhoneNumTV.text = station.telephone
                stationLytLatitudeTV.text = station.latitude
                stationLytLongitudeTV.text = station.longitude
                stationLytProvinceTV.text = station.province
                stationLytSupportedTypesTV.text = station.type.joinToString(separator = ", ")
            }
        }
    }


}

private class ChargingStationDiffCallback : DiffUtil.ItemCallback<ChargingStation>() {
    override fun areItemsTheSame(oldItem: ChargingStation, newItem: ChargingStation): Boolean {
        return (oldItem.longitude == newItem.longitude && oldItem.latitude == newItem.latitude)
    }

    override fun areContentsTheSame(oldItem: ChargingStation, newItem: ChargingStation): Boolean {
        return oldItem == newItem
    }

}