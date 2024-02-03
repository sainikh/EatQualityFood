# Cocktail Search App

This Android application allows users to search for cocktails based on their input. The app utilizes a Cocktail API to fetch drink data and display it to the user. Users can search for cocktails using keywords such as "Vodka," "Rum," or "Margarita."

## Features

- Search for cocktails based on keywords.
- View search results in a grid layout displaying drink names and thumbnails.
- Tap on a drink to view detailed information including instructions, ingredients, thumbnail, measure, and category.

## Screenshots

![Screenshot_1](https://github.com/sainikh/EatQualityFood/assets/47454954/55de9e1b-7b12-41ed-8aa0-ad3a76c8bb17)

*Screenshot: Search results grid view*

![Screenshot_2](https://github.com/sainikh/EatQualityFood/assets/47454954/605870c7-6891-4002-b5d7-8a75ef9988da)

*Screenshot: Detailed view of a selected drink*

## Getting Started

To get started with this app, follow these steps:

1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or a physical device.

## Technologies Used

- **Jetpack Compose**: The app UI is built using Jetpack Compose, which provides a modern and declarative way to build UIs in Android.
- **Dagger Hilt**: Dependency injection framework used to manage dependencies and facilitate testability of the app components.
- **Flow**: Used for state management, enabling reactive programming and efficient handling of data streams within the app.
- **Coil**: Image loading library used to fetch, display, and cache images efficiently.

## Dependencies

This app uses the following dependencies:

- Retrofit: for making API requests.
- Coil: for loading and caching images from URLs.
- Dagger Hilt: for dependency injection.
- Jetpack Compose: for building the app UI.
- Kotlin Coroutines: for asynchronous programming.
