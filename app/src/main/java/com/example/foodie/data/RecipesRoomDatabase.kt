package com.example.foodie.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Recipes::class], version = 1)
abstract class RecipesRoomDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    companion object {
        @Volatile
        private var INSTANCE: RecipesRoomDatabase? = null

        fun getDatabase(context: Context): RecipesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipesRoomDatabase::class.java,
                    "foodb"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}