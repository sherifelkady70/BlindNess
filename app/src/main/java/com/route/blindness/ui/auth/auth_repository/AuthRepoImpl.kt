package com.route.blindness.ui.auth.auth_repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.blindness.ui.auth.model.User
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepoInterface {
    override suspend fun register(userName: String, email: String, password: String): User {
        val authResult =
            Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        val user = User(authResult.user!!.uid,email,password)

        return user
    }
}