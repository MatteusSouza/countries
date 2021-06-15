package com.mattsouza.learnroom

import android.app.Application

class CountriesApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CountryRoomDatabase.CountryRoomDatabase.getDatabase(this) }
    val repository by lazy { CountryRepository(database.countryDao()) }
}