package com.route.blindness.ui.auth.auth_fragments.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val emailLiveData = MutableLiveData<String?>()
    val emailErrorLiveData = MutableLiveData<String?>()
    val passLiveData = MutableLiveData<String?>()
    val passErrorLiveData = MutableLiveData<String?>()

    fun navigateToHome(){
        try{
            Log.d("navigateToHome","before valid")
            if(!validate()) return
            Log.d("navigateToHome","after valid")
            onLoginClick!!.onClick()
            Log.d("navigateToHome","after invoke")
        }catch (e:Exception){

        }

    }

    fun gotoRegister(){
        onGoToRegisterClick!!.gotoRegisterFragment()
    }

    private fun validate() : Boolean{
        var isValid = true

        if(emailLiveData.value.isNullOrEmpty()){
            emailErrorLiveData.value = "Please Enter Your Email"
            isValid = false
        }else {
            emailErrorLiveData.value = null
        }
        if(passLiveData.value.isNullOrEmpty()){
            passErrorLiveData.value = "Please Enter Your Password"
            isValid = false
        }else {
            passErrorLiveData.value = null
        }
        if(passLiveData.value!!.length < 6){
            passErrorLiveData.value = "Must More Than 6 letters"
            isValid = false
        }else{
            passErrorLiveData.value = null
        }

        return isValid
    }

    interface OnGoToRegisterClick{
        fun gotoRegisterFragment()
    }
    var onGoToRegisterClick : OnGoToRegisterClick?=null
    interface OnLoginClick{
        fun onClick()
    }
    var onLoginClick : OnLoginClick?=null
}