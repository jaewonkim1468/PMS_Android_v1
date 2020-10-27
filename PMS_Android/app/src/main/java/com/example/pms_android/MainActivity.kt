package com.example.pms_android

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pms_android.fragments.*
import com.example.pms_android.login.MainLoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import splitties.activities.start

class MainActivity : AppCompatActivity() {
    var userId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!readAutoLogin()) {
            start<MainLoginActivity>()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, CalendarFragment())
            .commit()
        bottom_navigation.setOnNavigationItemSelectedListener(itemListener)

    }

    private val itemListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.calendar -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CalendarFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.information -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, InformationFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.notion -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,NotionFragment()
                ).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.introduce -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,IntroduceFragment()
                ).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.food_ic -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FoodFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            else -> {
                return@OnNavigationItemSelectedListener false
            }
        }
    }

    private fun readAutoLogin(): Boolean {
        val startShared = getSharedPreferences("auto_login", Context.MODE_PRIVATE)
        if (startShared.getString("get_id", "") ?: "" == "") {
            return false
        }
        return true
    }


}


