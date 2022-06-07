package com.example.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UserRepositoryModules {

   // @Provides
   // fun getFirebaseRepository() : UserRepository {
        //return type is UserRepository which returns FirebaseRepository() obj
        // it will tell dagger to use FirebaseRepository
        // @Provides annotation means whenever the UserRepository request is triggered,
        // it will called  getFirebaseRepository() fun and return FirebaseRepository() obj

   //     return FirebaseRepository()
   // }

//Method 2 to return  SqlRepository() obj via @Provides

//    @Provides
//    fun getSQLRepository(sqlRepository: SqlRepository) : UserRepository {
//        return SqlRepository()
//    }

//    Method 3 to return  SqlRepository() obj via @Binds

     @Binds
    abstract fun getSQLRepository(sqlRepository: SqlRepository) : UserRepository

}




//    Method 3 to return  SqlRepository() obj via @Binds



//@Module
//abstract class UserRepositoryModules {
//    @Binds
//    abstract fun getSQLRepository(sqlRepository: SqlRepository) : UserRepository
//     }

//UserRepository is binds to SqlRepository bcoz SqlRepository can creates obj by its own,
// so we have use binds, or else we had used @Provides


//--------------------------------------------------------------------------------------------

//Method 2 to return  SqlRepository() obj via @Provides

//@Module
// class UserRepositoryModules {
//    @Provides
//    fun getSQLRepository(sqlRepository: SqlRepository) : UserRepository {
//        return SqlRepository()
//    }
//      }
