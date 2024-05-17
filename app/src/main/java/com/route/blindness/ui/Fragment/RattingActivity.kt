package com.route.blindness.ui.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.route.blindness.R
import com.route.blindness.databinding.ActivityRattingBinding

class RattingActivity : AppCompatActivity() {
    lateinit var binding:ActivityRattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRattingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ratingbar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            binding.textview.text=fl.toString()
            when(ratingBar.rating.toInt()){
                1 ->binding.textview.text ="very Bad"
                2 ->binding.textview.text ="Bad"
                3 ->binding.textview.text ="Good"
                4 ->binding.textview.text ="Great"
                5 ->binding.textview.text ="Awesome"
                else ->binding.textview.text=" "
            }

        }
        binding.submitt2btn.setOnClickListener({
            binding.textcomment.setText(" ")
            Toast.makeText(this,"comment is submit",Toast.LENGTH_LONG).show()
        })
    }
}