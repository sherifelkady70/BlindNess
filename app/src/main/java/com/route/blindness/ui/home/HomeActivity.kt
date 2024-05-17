package com.route.blindness.ui.home

import com.route.blindness.ui.Fragment.SettingFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.route.blindness.R
import com.route.blindness.databinding.ActivityHomeBinding
import com.route.blindness.ui.auth.AuthActivity
import com.route.blindness.ui.Fragment.RealTimeFragment

class HomeActivity : AppCompatActivity() {
    var realTimeFragment = RealTimeFragment()
    var settingFragment= SettingFragment()

lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragments(realTimeFragment)
        setviews()
    }

    private fun setviews() {
        binding.navbar.setOnItemSelectedListener {
            if(it.itemId==R.id.nav_realtime) {
                replaceFragments(realTimeFragment)

            }
          else if (it.itemId==R.id.nav_setting){
                replaceFragments(settingFragment)
            }
            else if (it.itemId==R.id.nav_account)
            {
                startActivity(Intent(this, AuthActivity::class.java))
            }

            return@setOnItemSelectedListener true
        }
    }





    private fun replaceFragments(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(realTimeFragment.toString())
            .commit()

    }

}