package com.route.blindness.ui.auth.auth_fragments.login

import android.app.AlertDialog
import android.content.Intent
import android.icu.text.CaseMap.Title
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
    var dialog : AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_login,container,false)
        binding.lifecycleOwner =this
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        navigates()
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                showLoading()
            }else{
                hideLoading()
            }
        }

        viewModel.viewMessage.observe(viewLifecycleOwner){
            showDialog(it.titleOfError,it.descriptionOfError)
        }
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


    private fun showLoading(){
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.loading)
        dialog = builder.create()
        dialog?.show()
    }
    private fun hideLoading(){
        dialog?.dismiss()
    }

    private fun showDialog(
        title: String? = null,
        message: String? = null,
//        posBtnTitle: String? = null,
//        onPosBtnClick: (() -> Unit)? = null,
//        onNegBtnClick: (() -> Unit)? = null,
//        negBtnTitle: String? = null,


    ) {
        val myDialog = AlertDialog.Builder(activity)
        myDialog.setTitle(title)
        myDialog.setMessage(message)
//        posBtnTitle.let {
//            myDialog.setPositiveButton(posBtnTitle,
//                object : DialogInterface.OnClickListener{
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        dialog?.dismiss()
//                        onPosBtnClick?.invoke()
//                    }
//
//                })
//        }
//        negBtnTitle.let {
//            myDialog.setNegativeButton(negBtnTitle
//            ) { dialog, which ->
//                dialog?.dismiss()
//                onNegBtnClick?.invoke()
//            }
//        }

        myDialog.create().show()

    }
}