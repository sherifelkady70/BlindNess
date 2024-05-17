package com.route.blindness.ui.auth.auth_fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.blindness.ui.ViewMessage
import com.route.blindness.ui.auth.auth_repository.AuthRepoImpl
import com.route.blindness.ui.auth.auth_repository.AuthRepoInterface
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel : ViewModel() {
    val emailLiveData = MutableLiveData<String?>()
    val emailErrorLiveData = MutableLiveData<String?>()
    val passLiveData = MutableLiveData<String?>()
    val passErrorLiveData = MutableLiveData<String?>()
    val confirmPassLiveData = MutableLiveData<String?>()
    val confirmPassErrorLiveData = MutableLiveData<String?>()
    val fullNameLiveData = MutableLiveData<String?>()
    val fullNameErrorLiveData = MutableLiveData<String?>()
    val phoneNumLiveData = MutableLiveData<String?>()
    val phoneNumErrorLiveData = MutableLiveData<String?>()
    private val authRepo : AuthRepoInterface = AuthRepoImpl()
    val viewMessage = MutableLiveData<ViewMessage>()
    val isLoading = MutableLiveData<Boolean>()

    fun register() {
        if (!validate()) return
        viewModelScope.launch {
            isLoading.value = true
        try {
            authRepo.register(fullNameLiveData.value!!, emailLiveData.value!!, passLiveData.value!!)
            isLoading.value = false
            onGoToLoginScreen!!.onGoToLogin()
        } catch (e: Exception) {
            isLoading.value = false
            viewMessage.value = ViewMessage("Register Error", "$e")
        }
        }
    }
    private fun validate() : Boolean{
        var isValid = true
        if(fullNameLiveData.value.isNullOrEmpty()){
            fullNameErrorLiveData.value = "Please Enter Your Email"
            isValid = false
        }else {
            fullNameErrorLiveData.value = null
        }
        if(emailLiveData.value.isNullOrEmpty()){
            emailErrorLiveData.value = "Please Enter Your Email"
            isValid = false
        }else {
            emailErrorLiveData.value = null
        }
        if(passLiveData.value.isNullOrEmpty()){
            passErrorLiveData.value = "Please Enter Your Password"
            isValid = false
        }else if(passLiveData.value!!.length < 6){
            passErrorLiveData.value = "Must More Than 6 letters"
            isValid = false
        }else{
            passErrorLiveData.value = null
        }
        if(confirmPassLiveData.value.isNullOrEmpty()){
            confirmPassErrorLiveData.value = "Please Enter Your Password"
            isValid = false
        }else if(confirmPassLiveData.value!!.length < 6){
            confirmPassErrorLiveData.value = "Must More Than 6 letters"
            isValid = false
        }else{
            confirmPassErrorLiveData.value = null
        }
        if(phoneNumLiveData.value.isNullOrEmpty()){
            phoneNumErrorLiveData.value = "Please Enter Your Email"
            isValid = false
        }else {
            phoneNumErrorLiveData.value = null
        }

        return isValid
    }

    interface OnGoToLoginScreen{
        fun onGoToLogin()
    }
    var onGoToLoginScreen : OnGoToLoginScreen?=null

}