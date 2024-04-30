package com.route.blindness.ui.auth.auth_fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.blindness.ui.ViewMessage
import com.route.blindness.ui.auth.auth_repository.AuthRepoImpl
import com.route.blindness.ui.auth.auth_repository.AuthRepoInterface
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val emailLiveData = MutableLiveData<String?>()
    val emailErrorLiveData = MutableLiveData<String?>()
    val passLiveData = MutableLiveData<String?>()
    val passErrorLiveData = MutableLiveData<String?>()
    val viewMessage = MutableLiveData<ViewMessage>()
    val isLoading = MutableLiveData<Boolean>()
    private val authRepo : AuthRepoInterface = AuthRepoImpl()

    fun navigateToHome(){
        if(!validate()) return
        viewModelScope.launch{
            isLoading.value = true
            try {
                val user = authRepo.login(emailLiveData.value!!,passLiveData.value!!)
                isLoading.value = false
                onLoginClick!!.onClick()
            } catch (e: Exception) {
                isLoading.value = false
                viewMessage.value = ViewMessage("Error", "$e")
            }
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
        }else if(passLiveData.value!!.length < 6  ){
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