package com.example.dagger

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserRepositoryModules::class,NotificationServiceModules::class])
interface UserRegistrationComponent {

    //methods  that will provide the reqd dependency
   // fun getUserRegistrationService() : UserRegistrationService
   // fun getEmailService() : EmailService

    //here consumer is mainActivity, now we don't reqd getUserRegistrationService(), getEmailService()
    //all the task will be handled by inject method
    //flow till now ---> in this fun we have passed the obj
    //              ---> it will go to main activity & see where the Inject annotation is there
    //              ---> @Inject used in field determine we need injection in these field
    //              ---> @Inject used in constructor determine we tell to dagger how the obj is being created
    fun inject(mainActivity: MainActivity)


    @Component.Factory
    interface Factory{
        fun create( @BindsInstance retryCount: Int) : UserRegistrationComponent
    }
   // Factory will return UserRegistrationComponent obj,
    // whenever we want to pass runtime value, pass it in a create method
    // now, UserRegistrationComponent will have Int value, whenever dagger needs Int value it will use this properties
    // whenever the Component is ready, it will have the dynamic value
}



/*

Consumers  ----> Component -----> via Constructor
                          |
                          |----->via  Modules

=>modules create object containing abstract class, interface, builder pattern( In case of roomdb, retrofit),
  modules are the producers

=>Consumers  will tell Component to create obj ,
  Component will create obj with the help of Constructor & Modules.

 */