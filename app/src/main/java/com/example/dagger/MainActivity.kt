package com.example.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // whenever we want userRegistrationService obj we will call given below code
        val component = DaggerUserRegistrationComponent.builder().build()
        val userRegistrationService = component.getUserRegistrationService()
        userRegistrationService.register("test123@gmail.com","1111")


        //another example
        // If we want email service obj then, define in component interface
        val emailService = component.getEmailService()
    }
}


//==================================part 1=================================
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


//==================================part 2=================================

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

        val userRegistrationService = DaggerUserRegistrationComponent
                                      .builder()
                                      .build()
                                      .getUserRegistrationService


 */
