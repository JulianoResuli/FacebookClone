package com.example.fakefacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fakefacebook.databinding.ActivityFakeFacebookAppBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FakeFacebookAppActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FakeFacebookAppActivity"
    }

    private lateinit var navController: NavController
    private var _binding:ActivityFakeFacebookAppBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityFakeFacebookAppBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

       val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
       navController=navHostFragment.findNavController()


        binding.topNav.setupWithNavController(navController)


        //disables Top Navigation View when we are on login fragment
        //and  enables it when we go to home fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.homeFragment) {
                binding.topNav.visibility = View.VISIBLE
            } else if(destination.id ==R.id.loginFragment ){
                binding.topNav.visibility = View.GONE
            }
        }
    }
}
