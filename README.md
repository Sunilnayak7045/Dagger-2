# Dagger-2 Component Scopes (Activity and Application Level Components) & Dagger 2 Component dependencies


Scenario :
when user register some events should get triggered so analytics is used 

Code flow 

*application class consists Appcomponent which is  define there,
 Appcomponent  will provide AnalyticsService obj, 
 whenever we want  AnalyticsService obj we will refer to Appcomponent.  

*UserRegistrationComponent depends on analyticsService obj, so we provide AppComponent inside AppComponent we will get analyticsService obj

*whenever we want to make UserRegistrationComponent we will use factory


===================================== UserRepository.kt ===================================

-------------------------
Notification Service & User Repository 
depends on Analytics Service
-------------------------

@ActivityScope
class SqlRepository  @Inject constructor (val analyticsService: AnalyticsService) : UserRepository{

    override fun saveUser(email: String, password: String){
       Log.d( "DB","User saved in Sql db ")
        analyticsService.trackEvent("Save User","created in sql")

    }
}


===================================== UserRegistrationComponent.kt ===================================


-------------------------
Notification Service & User Repository 
depends on Analytics Service so in Factory the dependencies is passes AppComponent( AppComponent having analyticsService obj)
-------------------------

@ActivityScope
@Component(dependencies = [AppComponent::class],modules = [UserRepositoryModules::class,NotificationServiceModules::class])
interface UserRegistrationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create( @BindsInstance retryCount: Int, appComponent: AppComponent) : UserRegistrationComponent
    }
    //appComponent added because
    //Factory depends on component & component needs/depends  on obj
   // so we need to pass obj to Factory. so that it can return UserRegistrationComponent obj


}



===================================== MainActivity.kt ===================================


        val appComponent = (application as UserApplication).appComponent
        //first we access the appComponent

        val userRegistrationComponent = DaggerUserRegistrationComponent.factory().create(3, appComponent)
        //To make the UserRegistrationComponent  we had called the factory, inside create we had passed the appComponent
        //now dagger returned the UserRegistrationComponent  
        // then we had injected
        userRegistrationComponent.inject(this)


==============================================================================

Therefore, here activity level component depends on application level component


==============================================================================
