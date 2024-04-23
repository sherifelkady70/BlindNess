package com.route.blindness.ui.auth.auth_repository

import com.route.blindness.ui.auth.model.User

interface AuthRepoInterface {
    suspend fun register(userName : String , email: String , password:String) : User
}