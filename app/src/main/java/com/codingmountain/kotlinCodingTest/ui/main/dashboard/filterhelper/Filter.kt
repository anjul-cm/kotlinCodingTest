package com.codingmountain.kotlincodingtest.ui.main.dashboard.filterhelper

private const val TAG = "Filter"

enum class Filter(val filterName: String) {
    ADDRESS("Address"),
    CITY("City"),
    LATITUDE("Latitude"),
    LONGITUDE("Longitude"),
    PROVINCE("Province"),
    TELEPHONE("Telephone"),
    TYPE("Type");

}

data class FilterWithValue(val filter: Filter, val filterValue: String) {
    companion object {
        fun getAllFilters(): List<FilterWithValue> {
            return Filter.values().map { FilterWithValue(it, "") }
        }
    }
}