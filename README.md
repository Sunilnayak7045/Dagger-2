# Dagger-2 Field-Injections

 METHOD 1 :
        --> if main activity required 50 dependency then,
         we have to define 50 methods  that will provide the reqd dependency
        
        (
         @Component
         interface UserRegistrationComponent {

         //methods  that will provide the reqd dependency

         fun getUserRegistrationService() : UserRegistrationService
         fun getEmailService() : EmailService 
         
         )

         --> THEN we will call it an activity.
         ( val userRegistrationService = component.getUserRegistrationService() )
 
