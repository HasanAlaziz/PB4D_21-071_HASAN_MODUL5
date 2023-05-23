package com.example.navigasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.navigasi.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    val fragmentHome : Fragment = HomeFragment()
    val fragmentProfile : Fragment = ProfileFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = fragmentHome
    
    private lateinit var menu : Menu
    private lateinit var menuItem : MenuItem
    private lateinit var bottomNavigationView : BottomNavigationView

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNav()
    }
    private fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container , fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container , fragmentProfile).hide(fragmentProfile).commit()

        bottomNavigationView = binding.navVine
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemReselectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }

                R.id.navigation_profile -> {
                    callFragment(1, fragmentProfile)
                }
            }
        }
    }

    private fun callFragment(index : Int, fragment: Fragment){
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}