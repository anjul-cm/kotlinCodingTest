package com.codingmountain.kotlincodingtest.ui.auth.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codingmountain.kotlincodingtest.R
import com.codingmountain.kotlincodingtest.databinding.FragmentForgetPasswordBinding
import com.codingmountain.kotlincodingtest.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : Fragment() {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ForgetPasswordFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgetPasswordBinding.inflate(layoutInflater)

        setUpClickListenerForGoBackTextView()
        setClickListenerForResetPasswordBtn()

        observeResetPasswordStatusLiveData()

        return binding.root
    }

    private fun observeResetPasswordStatusLiveData() {
        viewModel.forgetPasswordStatusLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> {
                    binding.forgetPasswordFrgMsgTV.text = it.errorMsg
                    binding.forgetPasswordFrgMsgTV.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red_600
                        )
                    )
                }
                Resource.Loading -> {
                    binding.forgetPasswordFrgMsgTV.text = "Sending reset instructions."
                    binding.forgetPasswordFrgMsgTV.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.yellow_600
                        )
                    )
                }
                is Resource.Success -> {
                    binding.forgetPasswordFrgMsgTV.text =
                        "Instruction for resetting password has been mailed to you. "
                    binding.forgetPasswordFrgMsgTV.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.yellow_600
                        )
                    )
                }
            }
        }
    }

    private fun setClickListenerForResetPasswordBtn() {
        binding.forgetPasswordFrgSendResetLinkBtn.setOnClickListener {
            viewModel.resetPassword(
                binding.forgetPasswordFrgEmailET.text.toString()
            )
        }
    }

    private fun setUpClickListenerForGoBackTextView() {
        binding.forgetPasswordFrgGoBackTV.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}