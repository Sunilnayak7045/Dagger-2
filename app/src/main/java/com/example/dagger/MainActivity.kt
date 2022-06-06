package com.example.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userRepository = UserRepository()
        val emailService = EmailService()

        val userRegistrationService = UserRegistrationService(userRepository, emailService)
        userRegistrationService.register("test123@gmail.com","1111")
    }
}



/* BEFORE
        .
        .
        setContentView(R.layout.activity_main)

        val userRegistrationService = UserRegistrationService()
        userRegistrationService.register("test123@gmail.com","1111")
 */

/* AFTER
        .
        .
        setContentView(R.layout.activity_main)

        val userRepository = UserRepository()
        val emailService = EmailService()

        val userRegistrationService = UserRegistrationService(userRepository, emailService)
        //reqd dependency (userRepository, emailService) is passed to
         UserRegistrationService class  via constructor
         so, THIS IS CALLED CONSTRUCTOR DEPENDENCY INJECTION

        THIS IS CALLED MANUAL DEPENDENCY INJECTION
        bcoz we are manually injecting , if there are 10 activity then we have to create obj 10 times
 */