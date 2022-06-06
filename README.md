# Dagger-2 Inject-Component-Annotations


Consumers  ----> Component -----> via Constructor
                          |
                          |----->via  Modules

=>modules create object containing abstract class, interface, builder pattern( In case of roomdb, retrofit),
  modules are the producers

=>Consumers  will tell Component to create obj ,
  Component will create obj with the help of Constructor & Modules.
