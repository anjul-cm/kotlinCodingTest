/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 7:42 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.main.dashboard.filterhelper

import com.codingmountain.kotlincodingtest.ui.main.dashboard.customform.FormType

private const val TAG = "Filter"

enum class Filter(val filterName: String, val requiredFormType: FormType) {
    ADDRESS("Address", FormType.STRING),
    CITY("City", FormType.STRING),
    LATITUDE("Latitude", FormType.NUMBER),
    LONGITUDE("Longitude", FormType.NUMBER),
    PROVINCE("Province", FormType.STRING),
    TELEPHONE("Telephone", FormType.STRING),
    TYPE("Type", FormType.STRING);

}

data class FilterWithValue(val filter: Filter, val filterValue: String) {
    companion object {
        fun getAllFilters(): List<FilterWithValue> {
            return Filter.values().map { FilterWithValue(it, "") }
        }
    }
}