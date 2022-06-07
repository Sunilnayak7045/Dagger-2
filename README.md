# Dagger-2 Named-Annotation (Inbuilt-Qualifiers)


Before:

@Module
class NotificationServiceModules {

    @Provides
    fun getMessageService() : NotificationService {
        return MessageService()
    }

    @Provides
    fun getEmailService(emailService: EmailService) : NotificationService {

        return EmailService()
    }

}

error: [Dagger/DuplicateBinding] 
because NotificationService is bound with both fun getMessageService() & getEmailService() which will return NotificationService obj,
Dagger will get confused which NotificationService should be used

so we use Qualifiers (@Named Annotation)

---------------------------------------------------------------------------------------------------------------------------------------------


SOLUTION:

STEP 1:

@Module
class NotificationServiceModules {

    @Named("message")
    @Provides
    fun getMessageService() : NotificationService {
        return MessageService()
    }


    @Named("email")
    @Provides
    fun getEmailService(emailService: EmailService) : NotificationService {

        return EmailService()
    }

}



STEP 2: 

class UserRegistrationService @Inject constructor (

    private val userRepository: UserRepository ,
    
    @Named("message") private val notificationService: NotificationService
    
) {

    fun register(email: String, password: String){
        userRepository.saveUser(email, password)
        notificationService.send(email,"abc@gmail.com","user registered successfully")
    }

}


---------------------------------------------------------------------------------------------------------------------------------------------

Problem with @Named Annotation is if there is spelling mistake in @Named annotation (e.g @Named("message") )
then it will throw error: [Dagger/MissingBinding]
so we use custom Annotation 

