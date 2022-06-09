package com.example.dagger


import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


interface UserRepository{
    fun saveUser(email: String, password: String)
}


@ActivityScope
//whenever we reqd UserRepository class obj call the constructor
class SqlRepository  @Inject constructor (val analyticsService: AnalyticsService) : UserRepository{

    override fun saveUser(email: String, password: String){
       Log.d( "DB","User saved in Sql db ")
        analyticsService.trackEvent("Save User","created in sql")
        //when user register some events should get triggered so analytics is used

    }
}
class FirebaseRepository (val analyticsService: AnalyticsService)  : UserRepository{

    override fun saveUser(email: String, password: String){
       Log.d( "Firebase","User saved in Firebase")
        analyticsService.trackEvent("Save User","created in Firebase")
    }
}