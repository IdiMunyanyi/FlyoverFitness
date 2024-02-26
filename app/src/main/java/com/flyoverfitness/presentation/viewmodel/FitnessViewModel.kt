package com.flyoverfitness.presentation.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.flyoverfitness.presentation.data.FitnessDB
import com.flyoverfitness.presentation.data.FitnessDao
import com.flyoverfitness.presentation.repository.Repository
import com.flyoverfitness.presentation.data.FitnessEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FitnessViewModel(val repository: Repository): ViewModel() {
    fun addDetails(fitness: FitnessEntity){
        viewModelScope.launch{
            repository.addToRoom(fitness)
        }
    }

    val latestEntry = repository.getAll()
    val lastEntry: LiveData<FitnessEntity> = repository.lastEntry

}