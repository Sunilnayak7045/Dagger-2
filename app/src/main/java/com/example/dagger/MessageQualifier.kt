package com.example.dagger

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)

//RetentionPolicy define the annotation timeline, till runtime it will be valid
// RetentionPolicy.SOURCE = till compile time  it will be valid
// RetentionPolicy.CLASS = valid until the class gets load
annotation class MessageQualifier()
