package com.mattsouza.learnroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class CountryRoomDatabase {
    @Database(entities = arrayOf(Country::class), version = 1, exportSchema = false)
    public abstract class CountryRoomDatabase : RoomDatabase() {

        abstract fun countryDao(): CountryDao

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: CountryRoomDatabase? = null

            fun getDatabase(context: Context): CountryRoomDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryRoomDatabase::class.java,
                        "country_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
    }
}