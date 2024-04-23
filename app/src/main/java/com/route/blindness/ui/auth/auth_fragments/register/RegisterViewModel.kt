package com.route.blindness.ui.auth.auth_fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun register(){
        try {
            if(!validate()) return
            viewModelScope.launch {
                authRepo.register(fullNameLiveData.value!!,emailLiveData.value!!,passLiveData.value!!)
            }
        }catch (e:Exception){

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
        }else {
            passErrorLiveData.value = null
        }
        if(passLiveData.value!!.length < 6){
            passErrorLiveData.value = "Must More Than 6 letters"
            isValid = false
        }else{
            passErrorLiveData.value = null
        }
        if(confirmPassLiveData.value.isNullOrEmpty()){
            confirmPassErrorLiveData.value = "Please Enter Your Password"
            isValid = false
        }else {
            confirmPassErrorLiveData.value = null
        }
        if(confirmPassLiveData.value!!.length < 6){
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
}