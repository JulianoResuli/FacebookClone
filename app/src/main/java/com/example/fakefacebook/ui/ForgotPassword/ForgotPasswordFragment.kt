package com.example.fakefacebook.ui.ForgotPassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fakefacebook.R
import com.example.fakefacebook.databinding.FragmentForgotPasswordBinding
import com.example.fakefacebook.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private var _binding:FragmentForgotPasswordBinding?=null
    private val binding get()= _binding!!

    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        _binding = FragmentForgotPasswordBinding.bind(view)

        binding.passwordReset.setOnClickListener {
            auth.sendPasswordResetEmail(binding.editText.text.toString())
                .addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        val action=ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                }
        }
    }
}