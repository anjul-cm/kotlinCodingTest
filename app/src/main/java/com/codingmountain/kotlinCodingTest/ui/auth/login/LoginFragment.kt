package com.codingmountain.kotlincodingtest.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codingmountain.kotlincodingtest.R
import com.codingmountain.kotlincodingtest.databinding.FragmentLoginBinding
import com.codingmountain.kotlincodingtest.ui.auth.signup.SignUpFragment


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)

        setClickListenerToSignUpInsteadTextView()

        return binding.root
    }

    private fun setClickListenerToSignUpInsteadTextView() {
        binding.loginFrgSignUpInsteadTV.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_right,
                R.anim.slide_out_right
            ).replace(R.id.authActivity_container, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}