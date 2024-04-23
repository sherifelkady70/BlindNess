package com.route.blindness.ui.auth.auth_fragments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.route.blindness.R
import com.route.blindness.databinding.FragmentLoginBinding
import com.route.blindness.ui.auth.AuthActivity
import com.route.blindness.ui.home.HomeActivity


class LoginFragment : Fragment() {
    private lateinit var viewModel : LoginViewModel
    lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_login,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        navigates()
    }
    private fun navigates(){
        viewModel.onLoginClick =  object : LoginViewModel.OnLoginClick{
            override fun onClick() {
                startActivity(Intent(requireContext(),HomeActivity::class.java))
            }
        }
        viewModel.onGoToRegisterClick = object : LoginViewModel.OnGoToRegisterClick{
            override fun gotoRegisterFragment() {
                if (activity == null) return
                (activity as AuthActivity).navController
                    .navigate(R.id.action_loginFragment_to_registerFragment)
            }

        }
    }
}