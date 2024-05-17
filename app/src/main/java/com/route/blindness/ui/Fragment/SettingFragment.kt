package com.route.blindness.ui.Fragment

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.blindness.databinding.FragmentSettingBinding
import com.route.blindness.ui.auth.AuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.route.blindness.ui.StartActivity
import java.util.Locale

class SettingFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
lateinit var binding:FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSettingBinding.inflate(layoutInflater,container,false)
        return binding.root
        auth = FirebaseAuth.getInstance()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addacountBtn.setOnClickListener({
            startActivity(Intent(requireContext(), AuthActivity::class.java))
        })


        binding.logout1Btn.setOnClickListener({
            Firebase.auth.signOut()
            requireActivity().finish()
            requireActivity().finishAndRemoveTask()
            startActivity(Intent(requireContext(), AuthActivity::class.java))

            Toast.makeText(requireContext(), "Logout Successful", Toast.LENGTH_LONG).show()

        })
        binding.logout2Btn.setOnClickListener({
            Firebase.auth.signOut()
            requireActivity().finish()
            startActivity(Intent(requireContext(), AuthActivity::class.java))


            Toast.makeText(requireContext(), "Logout Successful", Toast.LENGTH_LONG).show()

        })

        binding.accountTv.setOnClickListener({
            startActivity(Intent(requireContext(), AuthActivity::class.java))

        })
        binding.arbBtn.setOnClickListener {
            setLocalear(requireContext(), "ar") // Change "fr" to the desired language code
            recreate(requireActivity())
        }
        binding.rateappTv.setOnClickListener({
            startActivity(Intent(requireContext(), RattingActivity::class.java))
        })
        binding.engBtn.setOnClickListener({
            setLocaleeg(requireContext(), "en") // Change "fr" to the desired language code
            recreate(requireActivity())

        })
        binding.shareappTv.setOnClickListener({
            val url="https://youtu.be/5GMwP9ppjdk"
            val intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra("Share this",url)
           startActivity(Intent.createChooser(intent,"Share using..."))

        })
    }

    fun setLocalear(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }
    fun setLocaleeg(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }



}












