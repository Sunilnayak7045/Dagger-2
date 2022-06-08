package com.example.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //field inject
    @Inject
    lateinit var userRegistrationService: UserRegistrationService

    @Inject
    lateinit var emailService: EmailService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // whenever we want userRegistrationService obj we will call given below code
        val component = DaggerUserRegistrationComponent
            .builder()
            .notificationServiceModules(NotificationServiceModules(3))
            .build()
        component.inject(this)

        //val userRegistrationService = component.getUserRegistrationService() // not reqd bcoz it is handled by inject method
        userRegistrationService.register("test123@gmail.com","1111")


        //another example
        // If we want email service obj then, define in component interface
       // val emailService = component.getEmailService() // not reqd bcoz it is handled by inject method
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


//==================================part 3=================================


/* BEFORE

        METHOD 1 :
        --> if main activity required 50 dependency then,
         we have to define 50 methods  that will provide the reqd dependency
         (
         @Component
         interface UserRegistrationComponent {

         //methods  that will provide the reqd dependency

         fun getUserRegistrationService() : UserRegistrationService
         fun getEmailService() : EmailService  )

         --> THEN we will call it an activity.
         ( val userRegistrationService = component.getUserRegistrationService() )

         METHOD 2 :
         --> reqd obj ke liye fields define karenge i.e use of fields injection
         ( @Inject
         lateinit var userRegistrationService: UserRegistrationService  ) ,

         --> jese hi component pe inject method call hoga
         (  component.inject(this) ),
          hamare fields initialize hoga.

         this is called fields injection




        .
        .
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



        */



/* AFTER
        .
        .

    @Inject
    lateinit var userRegistrationService: UserRegistrationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // whenever we want userRegistrationService obj we will call given below code
        val component = DaggerUserRegistrationComponent.builder().build()
        component.inject(this)
        userRegistrationService.register("test123@gmail.com","1111")


 */

/*


@Component
interface UserRegistrationComponent {
    fun inject(mainActivity: MainActivity)   }

        //flow till now ---> in this fun we have passed the obj
    //              ---> it will go to main activity & see where the Inject annotation is there
    //              ---> @Inject used in field determine we need injection in these field & dagger will provide us
    //              ---> @Inject used in constructor determine we tell to dagger how the obj is being created

 */