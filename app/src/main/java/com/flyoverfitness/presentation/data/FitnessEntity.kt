package com.flyoverfitness.presentation.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FitnessEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var height: Double,
    var nowDate: Long?,
    var weight: Double,
    var targetWeight: Double

)
