# Github Repo Project
###### Hello Payconiq code reviewer/s. This is the assignment you asked for. this project also includes an APK to install the app directly.

### Project Structure :

#### Architecture :
I used MVVM + Clean (Domain Layer) approach recommended by [developer.android](https://developer.android.com/jetpack/guide) as my project Architecture.

#### 3rd party libs :
+ RxJava/RxAndroid - Asynchronous programming with observable streams.
+ Hilt - Dependency injection.
+ Retrofit/OkHttp - Api calls and intercepting network layer.
+ Navigation component
+ Junit5 - Local unit tests
+ Junit4 and Hilt testing - Instrumentation tests.

### Hint :
+ I like to keep api data models separate from local data models (entities) and using only the data I really need for the UI. so, I used extension function to convert api response to my Entities.
+ I used custom NavigationHostFragment in order to Add fragments to stack instead of replacing them. (default NavHostFragment replaces fragments)
