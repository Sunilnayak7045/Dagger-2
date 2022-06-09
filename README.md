# Dagger-2 Sub Component Factory (pass the run time value via factory)


*UserRegistrationComponent depends on AppComponent,
 AppComponent has AnalyticsService obj defined

*SubComponent can directly access the parent Component's obj. 


Before: 

============UserRegistrationComponent ========

@ActivityScope

@Component(dependencies = [AppComponent::class], modules = [UserRepositoryModules::class,NotificationServiceModules::class])

interface UserRegistrationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create( @BindsInstance retryCount: Int, appComponent: AppComponent) : UserRegistrationComponent
    }

}

====================AppComponent.kt=========

@Singleton

@Component(modules = [AnalyticsModule::class])

interface AppComponent {

    fun getAnalyticsService(): AnalyticsService
    
}


=================MainActivity.kt==========


        val appComponent = (application as UserApplication).appComponent //Application level scope

        val userRegistrationComponent = DaggerUserRegistrationComponent.factory().create(3, appComponent)

        userRegistrationComponent.inject(this)



After:

===========UserRegistrationComponent ========

@ActivityScope
@Subcomponent(modules = [UserRepositoryModules::class,NotificationServiceModules::class])

interface UserRegistrationComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create( @BindsInstance retryCount: Int) : UserRegistrationComponent
    }

}

====================AppComponent.kt=========

@Singleton

@Component(modules = [AnalyticsModule::class])

interface AppComponent {

 fun getUserRegistrationComponentFactory() : UserRegistrationComponent.Factory
    //use factory to access the obj through SUB COMPONENT
}

=================MainActivity.kt==========


        val appComponent = (application as UserApplication).appComponent //Application level scope

        //val userRegistrationComponent = DaggerUserRegistrationComponent.factory().create(3, appComponent)

        // no need of DaggerUserRegistrationComponent bcoz we are accessing UserRegistrationComponent through AppComponent

        val userRegistrationComponent = appComponent.getUserRegistrationComponentFactory().create(3) //appComponent can either give sub component / factory

        userRegistrationComponent.inject(this)

