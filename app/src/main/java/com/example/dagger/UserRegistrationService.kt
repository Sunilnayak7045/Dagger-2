package com.example.dagger

import javax.inject.Inject


//whenever we reqd UserRegistrationService class obj call the constructor
//UserRegistrationService class is dependent on UserRepository & EmailService class
// so inject is used on UserRepository & EmailService class
//flow will be 1st UserRepository & EmailService obj will be created then UserRegistrationService obj
class UserRegistrationService @Inject constructor (
    private val userRepository: UserRepository ,
    private val notificationService: NotificationService
) {

    fun register(email: String, password: String){
        userRepository.saveUser(email, password)
        notificationService.send(email,"abc@gmail.com","user registered successfully")
    }

}

/*
1. Unit testing : not possible to test UserRegistrationService class,
                    we need UserRepository(),EmailService() obj ready
2. Single Responsibility : perform more than 1 work i.e obj creation  & register
3. Life time of these obj : whenever the class UserRegistrationService will destroy, obj will get destroy
4. extensible : in future we want to send user via sms not email so we have to create a new obj
 */


/* BEFORE (WITHOUT CONSTRUCTOR)

class UserRegistrationService {

    private val userRepository: UserRepository = UserRepository()
    private val emailService : EmailService = EmailService()

    fun register(email: String, password: String){
        userRepository.saveUser(email, password)
        emailService.send(email,"abc@gmail.com","user registered successfully")
    }

}


 */