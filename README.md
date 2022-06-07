# Dagger-2 Module-Provides-Binds


If a interface has multiple implementation

Step 1:
When @Inject is called dagger will get confused i.e from NotificationService which implementation should be picked (EmailService  or MessageService)
it will throw error:
error: [Dagger/MissingBinding] com.example.dagger.NotificationService cannot be provided without an @Provides-annotated method.



class UserRegistrationService @Inject constructor
(

    private val userRepository: UserRepository ,
    
    private val notificationService: NotificationService
    
) {

    fun register(email: String, password: String){
        userRepository.saveUser(email, password)
        notificationService.send(email,"abc@gmail.com","user registered successfully")
    }

}



Step 2:

=>Consumers will tell Component to create obj , 
Component will create obj with the help of Constructor & Modules(containing abstract class, interface, builder pattern( In case of roomdb, retrofit)).
but we cannot instantiate an interface i.e we cannot create obj. 
Generally, it contains abstract methods (except default and static methods introduced in Java8), which are incomplete



interface NotificationService{

    fun send(to: String, from: String, body: String)
    
}

class EmailService  @Inject constructor () : NotificationService {

    override fun send(to: String, from: String, body: String){
    
        Log.d( "email sendinggggg","Email Send")
        
    }
}

class MessageService  @Inject constructor () : NotificationService {

    override fun send(to: String, from: String, body: String){
    
        Log.d( "Message sendinggggg","Message Send")
        
    }
}

