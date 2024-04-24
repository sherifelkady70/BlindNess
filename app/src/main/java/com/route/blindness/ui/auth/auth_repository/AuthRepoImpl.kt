package com.route.blindness.ui.auth.auth_repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.route.blindness.ui.auth.model.User
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepoInterface {
    override suspend fun register(userName: String, email: String, password: String): User {
        val authResult =
            Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        val user = User(authResult.user!!.uid,email,password)
        val myDoc = Firebase.firestore.collection(User.STORE_USER_COLLECTION)
            .document(user.uId!!)
        Log.d("login","in register in repo ${user.uId}")
        myDoc.set(user)
        return user
    }

    override suspend fun login(email: String, password: String): User {
        val authResult =
            Firebase.auth.signInWithEmailAndPassword(email,password).await()
        val docRef = Firebase.firestore.collection(User.STORE_USER_COLLECTION)
            .document(authResult.user!!.uid)
        Log.d("login","in login in repo ${authResult.user!!.uid}")
        val snapshot = docRef.get().await()
        return snapshot.toObject(User::class.java)!!
    }
}