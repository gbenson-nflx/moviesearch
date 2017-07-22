# moviesearch

Sample app for test automation exercise

To build app with Gradle:
1. Edit `local.properties` file and modify 'sdk.dir' to point to your local copy of the Android SDK
2. Run `./gradlew installDebug` to compile and install the app

The app directory should be able to be imported directly into Android Studio.
By default, there should be two build configurations already defined:
* `app`: Installs and runs the application
* `espresso tests`: Installs app, runs Espresso tests

In this repo, an example Espresso test case is defined in `AppTitleEspressoTest.java`. Feel free to use this as a starting point for your tests.

Note that tests can also be built and run via the gradle command:
`./gradlew connectedAndroidTest`
