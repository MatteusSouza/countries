package com.mattsouza.learnroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CountryRoomDatabase {
    @Database(entities = arrayOf(Country::class), version = 1, exportSchema = false)
    public abstract class CountryRoomDatabase : RoomDatabase() {

        abstract fun countryDao(): CountryDao

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: CountryRoomDatabase? = null

            fun getDatabase(
                context: Context,
                scope: CoroutineScope
            ): CountryRoomDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryRoomDatabase::class.java,
                        "country_database"
                    )
                        .addCallback(CountryDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }

        private class CountryDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.countryDao())
                    }
                }
            }

            suspend fun populateDatabase(countryDao: CountryDao) {
                // Delete all content here.
                countryDao.deleteAll()

                // Add some country name.
                var country  = Country("Brazil")
                countryDao.insert(country)
                country = Country("Norway")
                countryDao.insert(country)
                country = Country("Netherlands")
                countryDao.insert(country)
                country = Country("Canada")
                countryDao.insert(country)

                // TODO: Add your own words!
            }
        }
    }
}