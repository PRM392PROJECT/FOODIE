package com.example.foodie

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.customview.CollectionPagerAdapter
import com.example.foodie.databinding.ActivityMainBinding
import com.example.foodie.ui.favorite.FavoriteFragment
import com.example.foodie.ui.history.HistoryFragment
import com.example.foodie.ui.home.HomeFragment
import com.example.foodie.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val home by lazy { HomeFragment() }
    private val favorite by lazy { FavoriteFragment() }
    private val profile by lazy { ProfileFragment() }
    private val history by lazy { HistoryFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavBottom()
        //setNavBottom2()
    }
    private fun setNavBottom(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        // add fragments to fragmentmanager to manage
        transaction.also {
            it.add(binding.content.id,home,"Home")
            it.add(binding.content.id,favorite,"Favorite").hide(favorite)
            it.add(binding.content.id,profile,"Profile").hide(profile)
            it.add(binding.content.id,history,"History").hide(history)
            it.commit()
        }
        // auto select home when open app
        binding.navbottom.selectedItemId = R.id.home
        binding.navbottom.setOnItemSelectedListener { v->
            when(v.itemId){
                R.id.home->{
                     showFragment(home)
                     true
                }
                R.id.like->{
                    showFragment(favorite)
                    true
                }
                R.id.profile->{
                    showFragment(profile)
                    true
                }
                R.id.history->{
                    showFragment(history)
                    true
                }
                else -> false
            }
        }
    }
    // show fragment when click item menu
    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragments = listOf(home,favorite,profile,history)
        // hide fragments
        for (fra in fragments){
            if(fra!= fragment){
                transaction.hide(fra)
            }
        }
        // show fragment
        transaction.show(fragment)
        transaction.commit()
    }

}