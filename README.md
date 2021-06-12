<h1 align="center">
  TheGameDB App with Clean Architecture Concept
</h1>

<p align="center">
  <a href="http://developer.android.com/index.html"><img alt="Platform" src="https://img.shields.io/badge/platform-Android-green.svg"></a>
  <img alt="api" src="https://img.shields.io/badge/API-23%2B-green?logo=android"/>
  <a href="http://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/kotlin-1.5.10-blue.svg"></a>
  <a href="https://developer.android.com/studio/releases/gradle-plugin"><img alt="Gradle" src="https://img.shields.io/badge/gradle-4.2.1-green.svg"></a>
  <a href="https://github.com/rsdiz/the-game-db-android-app/"><img alt="Star" src="https://img.shields.io/github/stars/rsdiz/the-game-db-android-app"></a>
  <a href="https://github.com/rsdiz/the-game-db-android-app/"><img alt="Circle CI" src="https://circleci.com/gh/rsdiz/the-game-db-android-app/tree/main.svg?style=shield"></a>
  <a href="https://snyk.io/test/github/rsdiz/the-game-db-android-app"><img alt="Known Vulnerabilities" src="https://snyk.io/test/github/rsdiz/the-game-db-android-app/badge.svg"></a>
  <a href="https://codecov.io/gh/rsdiz/the-game-db-android-app"><img alt="Codecov" src="https://codecov.io/gh/rsdiz/the-game-db-android-app/branch/main/graph/badge.svg"></a>
  <a href="https://www.codacy.com/gh/rsdiz/the-game-db-android-app/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rsdiz/the-game-db-android-app&amp;utm_campaign=Badge_Grade"><img alt="Codacy Badge" src="https://app.codacy.com/project/badge/Grade/953336905755496b97400007f4edb186"></a>
</p>

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

[<img src="https://github.com/rsdiz/the-game-db-android-app/blob/main/img/mad_scorecard/summary.png" width="70%"></img>](https://madscorecard.withgoogle.com/scorecards/1963449048/#summary)
[<img src="https://github.com/rsdiz/the-game-db-android-app/blob/main/img/mad_scorecard/jetpack.png" width="70%"></img>](https://madscorecard.withgoogle.com/scorecards/1963449048/#jetpack)
[<img src="https://github.com/rsdiz/the-game-db-android-app/blob/main/img/mad_scorecard/kotlin.png" width="70%"></img>](https://madscorecard.withgoogle.com/scorecards/1963449048/#kotlin)

## Reporting issues/Support

In case of any questions or reports the bug, feel free to open an issue, I will be glad to help.

## License

All code licensed under the [MIT License](http://www.opensource.org/licenses/mit-license.php). You are free to do whatever you want with this piece of code. Check it out the [LICENSE](LICENSE) file for more info.