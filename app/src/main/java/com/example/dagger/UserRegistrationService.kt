package com.example.dagger

class UserRegistrationService(private val userRepository: UserRepository ,
                              private val emailService: EmailService
) {

    fun register(email: String, password: String){
        userRepository.saveUser(email, password)
        emailService.send(email,"abc@gmail.com","user registered successfully")
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