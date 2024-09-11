package com.example.foodie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.foodie.databinding.ActivityMainBinding
import com.example.foodie.ui.favorite.FavoriteFragment
import com.example.foodie.ui.history.HistoryFragment
import com.example.foodie.ui.home.HomeFragment
import com.example.foodie.ui.profile.ProfileFragment

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
    }
    private fun setNavBottom(){
        binding.navbottom.setOnItemSelectedListener { v->
            when(v.itemId){
                R.id.home->{
                     setFragment(home)
                     true
                }
                R.id.like->{
                    setFragment(favorite)
                    true
                }
                R.id.profile->{
                    setFragment(profile)
                    true
                }
                R.id.history->{
                    setFragment(history)
                    true
                }
                else -> false
            }
        }
        binding.navbottom.selectedItemId = R.id.home

    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(binding.content.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}