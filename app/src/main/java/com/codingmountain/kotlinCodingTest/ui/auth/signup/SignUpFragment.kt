/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/26/22, 1:37 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.codingmountain.kotlincodingtest.databinding.FragmentSignUpBinding
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivityViewModel
import com.codingmountain.kotlincodingtest.utils.extensions.showToast


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val activityViewModel: AuthActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)

        setClickListenerToSignInTextView()
        setClickListenerToSignUpBtn()
        return binding.root
    }

    private fun setClickListenerToSignUpBtn() {
        binding.signUpFrgSignUpBtn.setOnClickListener {
            if (passwordMatches()) {
                activityViewModel.signUp(
                    binding.signUpFrgEmailET.text.toString(),
                    binding.signUpFrgPasswordET.text.toString()
                )
            } else {
                requireContext().showToast("Passwords do not match.")
            }
        }
    }

    private fun passwordMatches(): Boolean {
        return binding.signUpFrgPasswordET.text.toString() == binding.signUpFrgReEnterPasswordET.text.toString()
    }

    private fun setClickListenerToSignInTextView() {
        binding.signUpFrgLoginTV.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}