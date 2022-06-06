package com.example.dagger


import android.util.Log

class UserRepository {


    fun saveUser(email: String, password: String){
       Log.d( "DB","User saved in db")
    }
}