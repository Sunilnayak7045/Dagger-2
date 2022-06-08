package com.example.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class NotificationServiceModules() {

    @MessageQualifier
    //just a normal function that will just return object
    @Provides
    //dagger will pass the retryCount value
    fun getMessageService(retryCount : Int )  : NotificationService {
        //return type is NotificationService which returns MessageService() obj
        // it will tell dagger to use MessageService
        // @Provides annotation means whenever the NotificationService request is triggered,
        // it will called  getMessageService() fun and return MessageService() obj

        return MessageService(retryCount)
    }


    @Named("email")
    @Provides
    fun getEmailService(emailService: EmailService) : NotificationService {

        return EmailService()
    }

}