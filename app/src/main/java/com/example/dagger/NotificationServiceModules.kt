package com.example.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class NotificationServiceModules() {


    @ActivityScope
    @MessageQualifier
    //just a normal function that will just return object
    @Provides
    //dagger will pass the retryCount value
    //fun getMessageService(retryCount : Int )  : NotificationService {
    fun getMessageService( )  : NotificationService {
        //return type is NotificationService which returns MessageService() obj
        // it will tell dagger to use MessageService
        // @Provides annotation means whenever the NotificationService request is triggered,
        // it will called  getMessageService() fun and return MessageService() obj

        //return MessageService(retryCount)
        return MessageService(3)
    }


    @Named("email")
    @Provides
    fun getEmailService(emailService: EmailService) : NotificationService {

        return EmailService()
    }

}