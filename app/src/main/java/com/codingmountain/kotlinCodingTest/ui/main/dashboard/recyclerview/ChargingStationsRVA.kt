package com.codingmountain.kotlincodingtest.ui.main.dashboard.recyclerview

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

    class Holder(private val binding: LayoutStationBinding) :
        RecyclerView.ViewHolder(binding.root) {

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