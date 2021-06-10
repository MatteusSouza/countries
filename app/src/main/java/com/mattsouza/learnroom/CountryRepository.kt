package com.mattsouza.learnroom

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CountryRepository(private val countryDao: CountryDao) {

    val allCountries: Flow<List<Country>> = countryDao.getCountries()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(county: Country) {
        countryDao.insert(county)
    }
}