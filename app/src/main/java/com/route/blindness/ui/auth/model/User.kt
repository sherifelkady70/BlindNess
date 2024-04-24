package com.route.blindness.ui.auth.model

data class User(val uId : String? =null,val email : String? = null ,val password : String? = null) {
    companion object{
        const val STORE_USER_COLLECTION = "STORE_USER_COLLECTION"
    }
}
