# Dagger-2cSub Component

Before:

========UserRegistrationComponent.kt===== 

@ActivityScope

@Subcomponent(modules = [UserRepositoryModules::class,NotificationServiceModules::class])

interface UserRegistrationComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create( @BindsInstance retryCount: Int) : UserRegistrationComponent
    }
}

=========NotificationServiceModules.kt=====



@Module
class NotificationServiceModules() {

    @ActivityScope
    @MessageQualifier
    @Provides
    fun getMessageService(retryCount : Int )  : NotificationService {
        return MessageService(retryCount)
    }

}

=======AppComponent.kt=======


@Singleton

@Component(modules = [AnalyticsModule::class])

interface AppComponent {

    //fun getAnalyticsService(): AnalyticsService

     fun getUserRegistrationComponentFactory() : UserRegistrationComponent.Factory
    //use factory to access the SUB COMPONENT
    
    
}

=======MainActivity.kt=======


        val appComponent = (application as UserApplication).appComponent //Application level scope
        val userRegistrationComponent = appComponent.getUserRegistrationComponentFactory().create(3)
        userRegistrationComponent.inject(this)




After:

=======UserRegistrationComponent.kt======== 


@ActivityScope

@Subcomponent(modules = [UserRepositoryModules::class,NotificationServiceModules::class])

interface UserRegistrationComponent {

    fun inject(mainActivity: MainActivity)

   // @Subcomponent.Factory
   
   //  interface Factory{
   
   //     fun create( @BindsInstance retryCount: Int) : UserRegistrationComponent
   
   // }
   
}


=======NotificationServiceModules.kt======


@Module
class NotificationServiceModules() {


    @ActivityScope
    @MessageQualifier
    @Provides
    fun getMessageService( )  : NotificationService {
        return MessageService(3)
    }


}

===========AppComponent.kt====

@Singleton

@Component(modules = [AnalyticsModule::class])

interface AppComponent {

    fun getUserRegistrationComponent() : UserRegistrationComponent
    
}

==========MainActivity.kt====

        val appComponent = (application as UserApplication).appComponent //Application level scope
        val userRegistrationComponent = appComponent.getUserRegistrationComponent()
        userRegistrationComponent.inject(this)




