package com.codingmountain.kotlincodingtest.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.codingmountain.kotlincodingtest.R
import com.codingmountain.kotlincodingtest.databinding.FragmentLoginBinding
import com.codingmountain.kotlincodingtest.ui.auth.AuthActivityViewModel
import com.codingmountain.kotlincodingtest.ui.auth.forgetpassword.ForgetPasswordFragment
import com.codingmountain.kotlincodingtest.ui.auth.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val activityViewModel: AuthActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)

        setClickListenerToSignUpInsteadTextView()
        setClickListenerForLoginBtn()
        setClickListenerForForgetPwTextView()

        return binding.root
    }

    private fun setClickListenerForForgetPwTextView() {
        binding.loginFrgForgetPasswordTV.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_right,
                R.anim.slide_out_right
            ).replace(R.id.authAct_fragmentContainer, ForgetPasswordFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setClickListenerForLoginBtn() {
        binding.loginFrgLogInBtn.setOnClickListener {
            activityViewModel.logIn(
                binding.loginFrgEmailET.text.toString().trim(),
                binding.loginFrgPasswordET.text.toString().trim()
            )
        }
    }

    private fun setClickListenerToSignUpInsteadTextView() {
        binding.loginFrgSignUpTV.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_right,
                R.anim.slide_out_right
            ).replace(R.id.authAct_fragmentContainer, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}