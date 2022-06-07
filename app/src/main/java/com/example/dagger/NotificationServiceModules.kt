package com.example.dagger

import dagger.Module
import dagger.Provides


@Module
class NotificationServiceModules {


    //just a normal function that will just return object
    @Provides
    fun getMessageService() : NotificationService {
        //return type is NotificationService which returns MessageService() obj
        // it will tell dagger to use MessageService
        // @Provides annotation means whenever the NotificationService request is triggered,
        // it will called  getMessageService() fun and return MessageService() obj

        return MessageService()
    }

}