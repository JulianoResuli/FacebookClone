package com.example.fakefacebook.ui.TopNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakefacebook.R
import com.example.fakefacebook.databinding.FragmentMenuBinding
import com.example.fakefacebook.ui.Login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding:FragmentMenuBinding?=null
    private val binding get() =_binding!!

    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth= FirebaseAuth.getInstance()

        _binding= FragmentMenuBinding.bind(view)

        binding.buttonLogout.setOnClickListener {
             auth.signOut()
            val action=MenuFragmentDirections.actionMenuFragmentToLoginFragment()
            findNavController().navigate(action)

        }
    }

}