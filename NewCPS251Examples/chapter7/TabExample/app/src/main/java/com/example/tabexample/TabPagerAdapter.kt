package com.example.tabexample

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPagerAdapter(fa: FragmentActivity, private var tabCount: Int): FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fragment()
            2 -> Tab3Fragment()
            3 -> Tab4Fragment()
            else -> Tab1Fragment()
        }
    }

    override fun getItemCount(): Int {
        return tabCount
    }
}
