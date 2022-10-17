package com.example.tmovies.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.tmovies.R
import com.example.tmovies.databinding.ActivityMainBinding
import com.example.tmovies.utils.InternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var internetConnection: InternetConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showSelectedFragment(HomeFragment())
        bottomNavView()
        initNetworkConnection()
    }

    private fun initNetworkConnection() {
        internetConnection = InternetConnection(application)
        internetConnection.observe(this) { isConnected ->
            if(!isConnected) {
                binding.linear.visibility = View.VISIBLE
                binding.network.text = getString(R.string.offline)
            } else {
                binding.linear.visibility = View.GONE
            }
        }
    }

    private fun bottomNavView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {showSelectedFragment(HomeFragment())}
                R.id.settings -> {showSelectedFragment(SettingsFragment())}
            }
            true
        }
    }

    private fun showSelectedFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}