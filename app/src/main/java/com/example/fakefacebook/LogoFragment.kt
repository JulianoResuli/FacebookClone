package com.example.fakefacebook

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.fakefacebook.databinding.FragmentForgotPasswordBinding
import com.example.fakefacebook.databinding.FragmentLogoBinding


class LogoFragment : Fragment(R.layout.fragment_logo) {

    private var _binding: FragmentLogoBinding?=null
    private val binding get()= _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentLogoBinding.bind(view)

        val fade_out:Animation=AnimationUtils.loadAnimation(context,R.anim.fade_out)
        binding.logo.startAnimation(fade_out)

        val thread=Runnable {
            val action=LogoFragmentDirections.actionLogoFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        Handler().postDelayed(thread,3000)
    }
}