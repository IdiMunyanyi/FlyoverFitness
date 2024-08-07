package com.flyoverfitness.presentation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 1. Database Setup

@Database(entities = [FitnessEntity::class], version = 2)
abstract class FitnessDB : RoomDatabase() {
    abstract fun fitnessDao(): FitnessDao

    companion object {
        @Volatile
        private var INSTANCE: FitnessDB? = null

        fun getDatabase(context: Context): FitnessDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitnessDB::class.java,
                    "fitness_database"
                ).fallbackToDestructiveMigrationFrom(1).build()
                INSTANCE = instance
                instance
            }
        }
    }
}