package com.example.dagger

import android.util.Log
import javax.inject.Inject


//whenever we reqd EmailService class obj call the constructor
class EmailService  @Inject constructor () {
    fun send(to: String, from: String, body: String){
        Log.d( "email sendinggggg","Email Send")
    }
}