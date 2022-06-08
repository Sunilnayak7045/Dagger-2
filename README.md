# Dagger-2 Pass the value at run-time via Modules


val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"db_name")
.build()

//we get the applicationContext on runtime

// if we want roomdb obj through dagger, so we need method to tell dagger that some value we will be getting through run-time

// during run-time we will provide value to dagger, dagger will create the obj and give it to us

while building component, check whether which modules is accepting the properties, 

if we found properties then we have to make modules(val component = DaggerUserRegistrationComponent.builder()
            .notificationServiceModules(NotificationServiceModules(3)) //3 is the dynamic value passed
            .build() ) in activity




Method 1: 

pass value via Modules

===EmailService.kt file===

class MessageService(private val retryCount : Int)  : NotificationService {

    override fun send(to: String, from: String, body: String){
    
        Log.d( "Message sendinggggg","Message Send === $retryCount")
        
    }
    
}


===NotificationServiceModule.kt file===

@Module

class NotificationServiceModules(private val retryCount : Int) {

    @MessageQualifier
    @Provides
    fun getMessageService() : NotificationService {

        return MessageService(retryCount)
    }

===MainActivity.kt file===

val component = DaggerUserRegistrationComponent
                .builder()
                .notificationServiceModules(NotificationServiceModules(3))
                .build() 
                //3 is the dynamic value passed
            



Method 2:

pass value via Component (@BindsInstance )


