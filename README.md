Android ARCH Kotlin
=================

This is a template project using the concepts of android architecture components, Model View ViewModel pattern and Dependency injection technique to give a quick start to your projects. See more about [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/).

>Android architecture components are part of [Android Jetpack](https://developer.android.com/jetpack/). They are a collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.

Usage
------------------------
* Download the zip folder of this project to your projects folder.
* After extract the zip, follow [these instructions](https://stackoverflow.com/questions/1213430/how-to-fully-delete-a-git-repository-created-with-init) to delete the current git control version, then you can create your own.

* Rename the project to whatever you want by doing these follow steps:
    * Rename your project folder
    * On android manifest rename the package name
    * On your gradle module file rename the applicationId
    * On string resources xml file rename the **app_name** string
    * Rename the database name on **DatabaseModule** class.

* You're good to go!

Android Studio IDE setup
------------------------

For development, the latest version of Android Studio is required. The latest version can be downloaded from [here](https://developer.android.com/studio/).

Android ARCH Kotlin uses [ktlint](https://ktlint.github.io/) to enforce Kotlin coding styles. Here's how to configure it for use with Android Studio (instructions adapted from the ktlint [README](https://github.com/shyiko/ktlint/blob/master/README.md)):

- Close Android Studio if it's open

- Download ktlint using these [installation instructions](https://github.com/pinterest/ktlint/blob/master/README.md#installation)

- Apply ktlint settings to Android Studio using these [instructions](https://github.com/pinterest/ktlint/blob/master/README.md#-with-intellij-idea)

- Start Android Studio

Libraries used
------------------------
* [Anko][1]
* [Architecture][2]
* [Lifecycles][3]
* [LiveData][4]
* [Room][5]
* [ViewModel][6]
* [Dagger][7]
* [Gson][8]
* [Logging Interceptor][9]
* [Retrofit][10]

[1]: https://github.com/Kotlin/anko
[2]: https://developer.android.com/jetpack/arch/
[3]: https://developer.android.com/topic/libraries/architecture/lifecycle
[4]: https://developer.android.com/topic/libraries/architecture/livedata
[5]: https://developer.android.com/topic/libraries/architecture/room
[6]: https://developer.android.com/topic/libraries/architecture/viewmodel
[7]: https://github.com/google/dagger
[8]: https://github.com/google/gson
[9]: https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
[10]: https://github.com/square/retrofit