package com.example.dagger

import dagger.Component
import javax.inject.Singleton




@Singleton
@Component(modules = [AnalyticsModule::class])
interface AppComponent {
    //fun getAnalyticsService(): AnalyticsService

    //fun getUserRegistrationComponentFactory() : UserRegistrationComponent.Factory
    //use factory to access the SUB COMPONENT

    fun getUserRegistrationComponent() : UserRegistrationComponent
    //no factory needed, directly access the SUB COMPONENT
}

//AppComponent is the parent Component
//UserRegistrationComponent is the sub-Component

//UserRegistrationComponent(parent Component) can use the AppComponent(sub-Component)