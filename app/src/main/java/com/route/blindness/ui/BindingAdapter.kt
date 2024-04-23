package com.route.blindness.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun errorTextInputLayout(textInputLayout: TextInputLayout , error : String?){
    textInputLayout.error = error
}