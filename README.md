# TheGameDB App with Clean Architecture Concept

------
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)
![API](https://img.shields.io/badge/API-23%2B-green?logo=android)
[![Kotlin](https://img.shields.io/badge/kotlin-1.5.10-blue.svg)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-4.2.1-green.svg)](https://developer.android.com/studio/releases/gradle-plugin)
[![Star](https://img.shields.io/github/stars/rsdiz/the-game-db-android-app)](https://github.com/rsdiz/the-game-db-android-app/)
[![Circle CI](https://circleci.com/gh/rsdiz/the-game-db-android-app/tree/main.svg?style=shield)](https://github.com/rsdiz/the-game-db-android-app/)
[![Known Vulnerabilities](https://snyk.io/test/github/rsdiz/the-game-db-android-app/badge.svg)](https://snyk.io/test/github/rsdiz/the-game-db-android-app)
[![Code Coverage](https://codecov.io/gh/rsdiz/the-game-db-android-app/branch/main/graph/badge.svg)](https://codecov.io/gh/rsdiz/the-game-db-android-app)
[![Codacy](https://app.codacy.com/project/badge/Grade/953336905755496b97400007f4edb186)](https://www.codacy.com/gh/rsdiz/the-game-db-android-app/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rsdiz/the-game-db-android-app&amp;utm_campaign=Badge_Grade)

## What is this

Just a simple project due to finish "Dicoding Menjadi Android Developer Expert (MADE)" Final Submission and try new stuff on Android showing some games fetched from the [RAWG](https://api.rawg.io/docs/) (The Largest Games Database) API.

Feel free to use it just for learning or for your next Android application - I would be pretty honored!

## Requirements

- Android Studio
- JDK 1.8
- Android SDK

## Features

- Splash screen
- Get all games
- Search games
- Get all developers
- See all games by developers
- Add game to favorite list

## Tech Stack

- **Kotlin**, yes, I love it
- **Clean Architecture Concepts**
- **Modularization**
- **MVVM (Model-View-ViewModel)** Architecture pattern
- **Navigation Component** to navigate all activity or fragment in whole app
- **Coroutine Flow**, a stream that produces values asynchronously
- **Hilt** for dependency injection purposes
- **Glide** to handles image loading/caching
- **Mockito** for mock some classes on our tests
- **Espresso**, for our instrumentation tests
- **Retrofit** for our HTTP requests
- **ViewBinding** for more easily write code that interacts with views
- **SQLCipher** for encryption
- **CodeCov**, for generating our code coverage in the project
- **OkHttp** for certificate pinning
- **R8 with Proguard Rules** for obfuscation
- **Room** for our local persistence
- **CircleCI**, for making a full build of our project, as well as run all tests (unit & instrumentation), once we have any push into the repo.

## Mad Scorecard

[![Summary](https://github.com/rsdiz/the-game-db-android-app/blob/main/img/mad_scorecard/summary.png)](https://madscorecard.withgoogle.com/scorecards/1963449048/#summary)
[![Jetpack](https://github.com/rsdiz/the-game-db-android-app/blob/main/img/mad_scorecard/jetpack.png)](https://madscorecard.withgoogle.com/scorecards/1963449048/#jetpack)
[![Kotlin](https://github.com/rsdiz/the-game-db-android-app/blob/main/img/mad_scorecard/kotlin.png)](https://madscorecard.withgoogle.com/scorecards/1963449048/#kotlin)

## Reporting issues/Support

In case of any questions or reports the bug, feel free to open an issue, I will be glad to help.

## License

All code licensed under the [MIT License](http://www.opensource.org/licenses/mit-license.php). You are free to do whatever you want with this piece of code. Check it out the [LICENSE.md](LICENSE.md) file for more info.
