package com.route.blindness.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.route.blindness.R

class AuthActivity : AppCompatActivity() {

    lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initNav()
    }

    private fun initNav(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_auth_container) as NavHostFragment
        navController = navHostFragment.navController
    }
}