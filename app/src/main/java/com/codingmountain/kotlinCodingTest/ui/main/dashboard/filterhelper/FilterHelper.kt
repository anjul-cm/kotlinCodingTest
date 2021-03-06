/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/26/22, 12:43 AM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.main.dashboard.filterhelper

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingData
import androidx.paging.filter
import com.codingmountain.kotlincodingtest.databinding.LayoutAppliedFiltersBinding
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation
import com.codingmountain.kotlincodingtest.ui.main.dashboard.recyclerview.ChargingStationsRVA

private const val TAG = "FilterHelper"

class FilterHelper(
    private val context: Context,
    scrollingContainerForFilter: HorizontalScrollView,
    private val recyclerViewAdapter: ChargingStationsRVA,
    private val fragmentManager: FragmentManager
) {

    private val filterContainer = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
    }

    private val addedFilters = mutableListOf<FilterWithValue>()

    init {
        scrollingContainerForFilter.addView(filterContainer)
    }

    fun showAddFilterFragment() {
        AddFilterBottomSheetFragment.onNewInstance(addedFilters).apply {

            addOnAddFilterCallback(object : AddFilterBottomSheetFragment.OnAddFilterClick {
                override fun onAddFilterClick(filterList: List<FilterWithValue>) {
                    addedFilters.clear()
                    addedFilters.addAll(filterList)
                    showFilters()
                }
            })

            show(
                this@FilterHelper.fragmentManager, null
            )
        }
    }

    private fun getView(filterWithValue: FilterWithValue): View {
        val binding = LayoutAppliedFiltersBinding.inflate(
            LayoutInflater.from(context), filterContainer, false
        )

        binding.chosenFilterForPatientLytFilterTV.text =
            getTextForBtn(filterWithValue.filter.filterName, filterWithValue.filterValue)

        binding.chosenFilterForPatientLytRemoveFilterIV.setOnClickListener {
            addedFilters.remove(filterWithValue)
            showFilters()
            recyclerViewAdapter.refresh()
        }

        binding.root.setOnClickListener {
            showAddFilterFragment()
        }

        return binding.root
    }


    private fun getTextForBtn(filterName: String, filterValue: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                "<b>$filterName</b>: $filterValue", Html.FROM_HTML_MODE_LEGACY
            )
        } else {
            Html.fromHtml(
                "<b>$filterName</b>: $filterValue"
            )
        }
    }


    private fun showFilters() {
        filterContainer.removeAllViews()
        addedFilters.forEach { filterWithValue ->
            filterContainer.addView(
                getView(filterWithValue)
            )
        }
        recyclerViewAdapter.refresh()
    }

    fun filterData(
        data: PagingData<ChargingStation>,
        searchQuery: String
    ): PagingData<ChargingStation> {
        return data.filter {
            if (searchQuery.isNotBlank() && searchQuery.lowercase() !in it.name.lowercase()) {
                return@filter false
            }
            var visible = true
            addedFilters.forEach { filter ->
                visible = when (filter.filter) {
                    Filter.ADDRESS -> matchesWithValue(it.address, filter.filterValue, true)
                    Filter.CITY -> matchesWithValue(it.city, filter.filterValue, false)
                    Filter.LATITUDE -> liesWithinFilter(it.latitude, filter.filterValue)
                    Filter.LONGITUDE -> liesWithinFilter(it.longitude, filter.filterValue)
                    Filter.PROVINCE -> matchesWithValue(it.province, filter.filterValue, false)
                    Filter.TELEPHONE -> matchesWithValue(it.telephone, filter.filterValue, false)
                    Filter.TYPE -> liesInList(it.type, filter.filterValue)
                }
                if (!visible) {
                    return@filter false
                }
            }
            visible
        }

    }

    private fun matchesWithValue(
        value: String,
        filterValue: String?,
        subStringMatch: Boolean
    ): Boolean {
        filterValue?.let {
            return if (subStringMatch) {
                it.trim().lowercase() in value.trim().lowercase()
            } else {
                it.trim().lowercase() == value.trim().lowercase()
            }
        }
        return true
    }

    private fun liesInList(typeList: List<String>, filterValue: String?): Boolean {
        filterValue?.let {
            return filterValue.trim().lowercase() in typeList.map { it.trim().lowercase() }

        }
        return true
    }


    private fun liesWithinFilter(value: String, filterValue: String?): Boolean {
        try {
            filterValue?.let {
                val lowerRange = filterValue.toFloat().toInt() - 10
                val upperRange = filterValue.toFloat().toInt() + 10
                return (value.toFloat().toInt() in lowerRange..upperRange)
            }
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }


}

