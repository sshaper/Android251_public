package com.scottshaper.duel_pane_layouts_dynamic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView

const val STAR_SIGN_ID = "STAR_SIGN_ID"

interface StarSignListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), StarSignListener {

    var isDualPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        isDualPane = findViewById<View>(R.id.star_sign_detail) != null

        if (savedInstanceState == null){
            findViewById<FragmentContainerView>(R.id.fragment_container)?.let {
                frameLayout -> val listFragment = ListFragment()

                supportFragmentManager.beginTransaction().add(frameLayout.id, listFragment).commit()
            }
        }
    }

    override fun onSelected(starSignId: Int) {

        findViewById<FragmentContainerView>(R.id.fragment_container)?.let{
                frameLayout -> val detailFragment = DetailFragment.newInstance(starSignId)

            //THE ADDTOBACKSTACK ALLOWS THE LIST TO BE SHOWN WHEN THE BACK BUTTON IS PRESSED
            supportFragmentManager.beginTransaction().replace(frameLayout.id, detailFragment).addToBackStack(null).commit()
        }

        //FOR THIS EXERCISE WE NO LONGER DO THE DUAL SCREEN WE ARE JUST REPLACING THE LIST WITH THE FRAGMENT SO THIS IS NOT NEEDED.
        /*if (isDualPane) {

            val detailFragment =
                supportFragmentManager.findFragmentById(R.id.star_sign_detail) as DetailFragment
            detailFragment.setStarSignData(id)
        } else {
            val detailIntent = Intent(this,  DetailActivity::class.java).apply {
                putExtra(STAR_SIGN_ID, id)
            }
            startActivity(detailIntent)
        }*/
    }
}