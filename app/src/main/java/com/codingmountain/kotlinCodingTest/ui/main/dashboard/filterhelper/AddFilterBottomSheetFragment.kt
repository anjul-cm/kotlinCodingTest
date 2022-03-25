package com.codingmountain.kotlincodingtest.ui.main.dashboard.filterhelper

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import com.codingmountain.kotlincodingtest.databinding.FragmentAddFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val TAG = "AddFilterBottomSheetFra"

private const val APPLIED_FILTERS_PARAMS = "AppliedFilterParams"

class AddFilterBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentAddFilterBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var onAddClickCallback: OnAddFilterClick? = null

    private val formHashMap = hashMapOf<Filter, EditText>()

    private var appliedFilters = listOf<FilterWithValue>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            appliedFilters = Gson().fromJson(
                it.getString(APPLIED_FILTERS_PARAMS),
                object : TypeToken<List<FilterWithValue>>() {}.type
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFilterBottomSheetBinding.inflate(layoutInflater)

        addAvailableFilters()
        addClickListenerToAddFilterBtn()
        makeSureViewIsNotObscuredOnKeyboardAppearing()
        return binding.root
    }

    private fun addClickListenerToAddFilterBtn() {
        binding.addFilterFrgAddFilterBtn.setOnClickListener {
            onAddClickCallback?.onAddFilterClick(getFinalFilterList())
            dismiss()
        }
    }

    private fun getFinalFilterList(): List<FilterWithValue> {
        val requiredList = mutableListOf<FilterWithValue>()
        formHashMap.forEach { form ->
            if (form.value.text.isNotBlank()) {
                requiredList.add(
                    FilterWithValue(
                        form.key,
                        form.value.text.toString().trim()
                    )
                )
            }
        }
        return requiredList
    }

    private fun addAvailableFilters() {
        binding.addFilterFrgFilterFormContainer.removeAllViews()
        FilterWithValue.getAllFilters().forEach { filterWithValue ->
            val editText = EditText(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                hint = filterWithValue.filter.filterName
            }
            formHashMap[filterWithValue.filter] = editText
            setFilterValueIfAlreadyFilterAdded(editText, filterWithValue)
            binding.addFilterFrgFilterFormContainer.addView(editText)
        }
    }

    private fun setFilterValueIfAlreadyFilterAdded(
        editText: EditText,
        filterWithValue: FilterWithValue
    ) {
        appliedFilters.forEach {
            if (it.filter == filterWithValue.filter) {
                editText.setText(it.filterValue)
                return
            }
        }
    }

    private fun makeSureViewIsNotObscuredOnKeyboardAppearing() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            dialog?.window?.decorView?.setOnApplyWindowInsetsListener { _, windowInsets ->
                val imeHeight = windowInsets.getInsets(WindowInsets.Type.ime()).bottom
                binding.root.setPadding(
                    0,
                    0,
                    0,
                    imeHeight
                )
                windowInsets
            }
        } else {
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    fun addOnAddFilterCallback(callback: OnAddFilterClick) {
        onAddClickCallback = callback
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    interface OnAddFilterClick {
        fun onAddFilterClick(filterList: List<FilterWithValue>)
    }

    companion object {
        fun onNewInstance(filterList: List<FilterWithValue>) =
            AddFilterBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(APPLIED_FILTERS_PARAMS, Gson().toJson(filterList))
                }
            }
    }

}