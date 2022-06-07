package com.example.dagger

import android.util.Log
import javax.inject.Inject


interface NotificationService{
    fun send(to: String, from: String, body: String)
}

//whenever we reqd EmailService class obj call the constructor
class EmailService  @Inject constructor () : NotificationService {
    override fun send(to: String, from: String, body: String){
        Log.d( "email sendinggggg","Email Send")
    }
}


class MessageService  : NotificationService {
    override fun send(to: String, from: String, body: String){
        Log.d( "Message sendinggggg","Message Send")
    }
}