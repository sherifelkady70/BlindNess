package com.route.blindness.ui.auth.auth_fragments.register

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.route.blindness.R
import com.route.blindness.databinding.FragmentRegisterBinding
import com.route.blindness.ui.ViewMessage
import com.route.blindness.ui.auth.AuthActivity

class RegisterFragment : Fragment() {
    lateinit var viewModel : RegisterViewModel
    lateinit var binding : FragmentRegisterBinding
    private var dialog : AlertDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_register,container,false)
        binding.vm = viewModel
        binding.lifecycleOwner =this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        navigateToLogin()
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

    private fun navigateToLogin(){
        viewModel.onGoToLoginScreen = object : RegisterViewModel.OnGoToLoginScreen{
            override fun onGoToLogin() {
                if(activity==null) return
                (activity as AuthActivity).navController
                    .navigate(R.id.action_registerFragment_to_loginFragment)
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