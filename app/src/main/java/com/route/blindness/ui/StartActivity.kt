package com.route.blindness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.route.blindness.R
import com.route.blindness.databinding.StartBinding
import com.route.blindness.ui.auth.AuthActivity
import com.route.blindness.ui.home.HomeActivity

lateinit var binding:StartBinding
class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=StartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAccept.setOnClickListener({
            startActivity(Intent(this, HomeActivity::class.java))
        })
    }
}