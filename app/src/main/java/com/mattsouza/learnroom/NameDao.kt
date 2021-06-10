package com.mattsouza.learnroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NameDao {

    @Query("SELECT * FROM countries_table ORDER BY country_name ASC")
    fun getCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(country: Country)

    @Query("DELETE FROM countries_table")
    suspend fun deleteAll()

}