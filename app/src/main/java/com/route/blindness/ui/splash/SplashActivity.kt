package com.route.blindness.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.route.blindness.R
import com.route.blindness.ui.auth.AuthActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToAuthActivity()
    }


    private fun navigateToAuthActivity(){
        Handler(mainLooper).postDelayed({
         startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
            finish()
        },2000)
    }
}