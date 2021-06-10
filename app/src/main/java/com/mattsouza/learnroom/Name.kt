package com.mattsouza.learnroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey
    @ColumnInfo(name = "country_name") val country: String
)
