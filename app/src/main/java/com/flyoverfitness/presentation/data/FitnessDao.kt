package com.flyoverfitness.presentation.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDao {
    @Insert
    suspend fun addDetails(fitnessEntity: FitnessEntity)

    @Query("SELECT * FROM FitnessEntity ORDER BY id DESC")
    fun getAll(): Flow<List<FitnessEntity>>

    @Query("SELECT * FROM FitnessEntity ORDER BY id DESC LIMIT 1")
    fun getLastEntry(): LiveData<FitnessEntity>
}