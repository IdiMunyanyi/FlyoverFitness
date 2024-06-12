package com.flyoverfitness.presentation.repository


import androidx.lifecycle.LiveData
import com.flyoverfitness.presentation.data.FitnessDB
import com.flyoverfitness.presentation.data.FitnessEntity
import kotlinx.coroutines.flow.Flow

class Repository(val fitnessDb: FitnessDB) {
    suspend fun addToRoom(fitnessEntity: FitnessEntity){
        fitnessDb.fitnessDao().addDetails(fitnessEntity)
    }

    val getAll: LiveData<List<FitnessEntity>> = fitnessDb.fitnessDao().getAll()

    val lastEntry: LiveData<FitnessEntity> = fitnessDb.fitnessDao().getLastEntry()
}