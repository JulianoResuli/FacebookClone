package com.example.fakefacebook.ui.TopNav

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.fakefacebook.FakeFacebookAppActivity
import com.example.fakefacebook.R

import com.google.firebase.auth.FirebaseAuth

class HomeFragment: Fragment(R.layout.fragment_home) {

     companion object {
        private const val TAG = "HomeFragment"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)




    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu,menu)
    }

}