package com.example.dagger


import android.util.Log
import javax.inject.Inject


//whenever we reqd UserRepository class obj call the constructor
class UserRepository  @Inject constructor () {


    fun saveUser(email: String, password: String){
       Log.d( "DB","User saved in db")
    }
}