# Dagger-2 Activity-level Singleton


Singleton:

Just one instance i.e Single Object for the whole application

accessing obj through component ->  use @Singleton  annotation on the component also



@Singleton has a scope 

e.g Singleton obj define in component, so  @Singleton  defines a scope, 
if we access that obj outside the scope, it will create new obj

i.e if  component defined in activity, then if the activity is created again then due to screen rotation  then it will create new obj,
here Singleton obj  has a boundaries, i.e component

solution could be applicationlevel Singleton 

because applicationlevel is the top level then activity is created then fragments are created. 

activity & fragments are created and destroyed but the application runs. so applicationlevel Singleton  is preferred.

so defined component in application, 

until the application runs we will have component, 

inside the component the  Singleton obj maintained  i.e obj will have same hexcode.


![Screenshot (36)](https://user-images.githubusercontent.com/47368515/172614819-f6ce2d8e-7807-4227-a158-aaf4d0423a23.png)

On screen rotation the hexcode value will get change


Case 1 : 

**if we want this NotificationService obj as singleton

    @Singleton
    @MessageQualifier
    @Provides
    fun getMessageService(retryCount : Int )  : NotificationService {
        return MessageService(retryCount)
    }


Case 2 :

**if we want this EmailService obj as singleton

==================MainActivity.kt=========


    lateinit var emailService: EmailService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerUserRegistrationComponent.factory().create(3)
        component.inject(this)
        userRegistrationService.register("test123@gmail.com","1111")

    }

/* Ctrl+click on EmailService, but we have inject on  EmailService  class so create singleton on the component also.
/* so Ctrl+click on inject (line having component.inject)

============ EmailService.kt=========  

@Singleton

class EmailService  @Inject constructor () : NotificationService {

    override fun send(to: String, from: String, body: String){
        Log.d( "email sendinggggg","Email Send")
    }
}


============ UserRegistrationComponent.kt=========  


@Singleton
@Component(modules = [UserRepositoryModules::class,NotificationServiceModules::class])

interface UserRegistrationComponent {


    fun inject(mainActivity: MainActivity)


    @Component.Factory
    interface Factory{
        fun create( @BindsInstance retryCount: Int) : UserRegistrationComponent
    }
}
