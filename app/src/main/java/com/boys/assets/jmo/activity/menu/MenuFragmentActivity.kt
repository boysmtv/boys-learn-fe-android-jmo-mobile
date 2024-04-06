package com.boys.assets.jmo.activity.menu

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.multidex.MultiDex
import com.boys.assets.jmo.R
import com.boys.assets.jmo.activity.menu.fragment.posts.PostsFragment
import com.boys.assets.jmo.activity.menu.fragment.home.HomeFragment
import com.boys.assets.jmo.activity.menu.fragment.contact.ContactFragment
import com.boys.assets.jmo.activity.menu.fragment.profile.ProfileFragment
import com.boys.assets.jmo.databinding.ActivityMenuMainBinding

class MenuFragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val menu : Menu = binding.bottomView.menu
        selectedMenu(menu.getItem(0))
        binding.bottomView.setOnNavigationItemSelectedListener {
                item: MenuItem ->  selectedMenu(item)
            false
        }
    }

    private fun selectedMenu(item : MenuItem) {
        item.isChecked = true
        when(item.itemId) {
            R.id.homeMenu -> selectedFragment(HomeFragment.getInstance())
            R.id.financeMenu -> selectedFragment(PostsFragment.getInstance())
            R.id.portofolioMenu -> selectedFragment(ContactFragment.getInstance())
            R.id.profileMenu -> selectedFragment(ProfileFragment.getInstance())
        }
    }

    private fun selectedFragment(fragment: Fragment) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rootFragment, fragment)
        transaction.commit()
    }
}