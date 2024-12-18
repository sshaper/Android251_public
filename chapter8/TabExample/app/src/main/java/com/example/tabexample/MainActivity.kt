package com.example.tabexample

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tabexample.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(),Tab1Fragment.OnFragmentInteractionListener,
    Tab2Fragment.OnFragmentInteractionListener,
    Tab3Fragment.OnFragmentInteractionListener,
    Tab4Fragment.OnFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root) // Set the content view to the root of the binding

        setSupportActionBar(binding.toolbar)

        //call configureTabLayout
        configureTabLayout()
    }


    private fun configureTabLayout() {
        repeat (4) {
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }
        val adapter = TabPagerAdapter(this, binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.baseline_star_24)
                    tab.text = null // Remove text if you only want the icon
                }
                1 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.baseline_access_time_24)
                    tab.text = null // Remove text if you only want the icon
                }
                else -> {
                    // Set text for other tabs, or add more icons as needed
                    tab.text = "Tab ${(position + 1)} Item"
                }
            }
        }.attach()
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_fragment1 -> {
                binding.viewPager.currentItem = 0 // Switch to Fragment 1
                true
            }
            R.id.nav_fragment2 -> {
                binding.viewPager.currentItem = 1 // Switch to Fragment 2
                true
            }
            R.id.nav_fragment3 -> {
                binding.viewPager.currentItem = 2 // Switch to Fragment 3
                true
            }
            R.id.nav_fragment4 -> {
                binding.viewPager.currentItem = 3 // Switch to Fragment 4
                true
            }
            R.id.action_settings -> {
                // Handle the settings action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

