package com.example.dagger


import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


interface UserRepository{
    fun saveUser(email: String, password: String)
}


@ApplicationScope
//whenever we reqd UserRepository class obj call the constructor
class SqlRepository  @Inject constructor () : UserRepository{

    override fun saveUser(email: String, password: String){
       Log.d( "DB","User saved in Sql db ")
    }
}
class FirebaseRepository   : UserRepository{

    override fun saveUser(email: String, password: String){
       Log.d( "Firebase","User saved in Firebase")
    }
}