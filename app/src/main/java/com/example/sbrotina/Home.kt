package com.example.sbrotina

import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sbrotina.databinding.ActivityHomeBinding
import com.example.sbrotina.timer.TimerFragment
import timber.log.Timber

@Suppress("DEPRECATION")
class Home : AppCompatActivity() {

    private  val homeFragment = HomeFragment()
    private val timerFragment = TimerFragment()
    private val calenderFragment = CalenderFragment()
    private val  settingsFragment = SettingsFragment()


    private  lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())


        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")

        replaceFragment(homeFragment)

        binding.BottomNavMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_calender -> replaceFragment(calenderFragment)
                R.id.ic_pomodoro -> replaceFragment(timerFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment)
    {
        if (fragment != null)
        {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }
}