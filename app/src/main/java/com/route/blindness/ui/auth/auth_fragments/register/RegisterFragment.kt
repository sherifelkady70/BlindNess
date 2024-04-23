package com.route.blindness.ui.auth.auth_fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.route.blindness.R
import com.route.blindness.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var viewModel : RegisterViewModel
    lateinit var binding : FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_register,container,false)
        binding.vm = viewModel
        return binding.root
    }

}