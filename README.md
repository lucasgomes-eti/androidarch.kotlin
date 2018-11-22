# Android ARCH Kotlin

This is a template project using the concepts of android architecture components, Model View ViewModel pattern and Dependency injection technique to give a quick start to your projects. See more about [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/).

>Android architecture components are part of [Android Jetpack](https://developer.android.com/jetpack/). They are a collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.

## Usage
* Download the zip folder of this project to your projects folder.
* After extract the zip, follow [these instructions](https://stackoverflow.com/questions/1213430/how-to-fully-delete-a-git-repository-created-with-init) to delete the current git control version, then you can create your own.

* Rename the project to whatever you want by doing these follow steps:
    * Rename your project folder
    * On android manifest rename the package name using Refactor > Rename... > Rename package > Do Refactor
    * On your gradle module file rename the applicationId
    * On string resources xml file rename the **app_name** string
    * Rename the database name on **DatabaseModule** class.

* You're good to go!

## Libraries used
* [Anko](https://github.com/Kotlin/anko)
* [ARCH ViewModel, LiveData and ROOM](https://developer.android.com/topic/libraries/architecture/adding-components)
* [Dagger](https://github.com/google/dagger)
* [Gson](https://github.com/google/gson)
* [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
* [Retrofit](https://github.com/square/retrofit)