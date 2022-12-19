package com.example.mobileteamproj

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities=[Food::class],version=1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class FoodDB : RoomDatabase() {
    abstract val foodDao: FoodDao

    companion object {
        private var INSTANCE: FoodDB? = null

        @Synchronized
        fun getInstance(context: Context): FoodDB? {
            if (INSTANCE == null) {
                synchronized(FoodDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDB::class.java, "food.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}