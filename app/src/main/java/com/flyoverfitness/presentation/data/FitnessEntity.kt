package com.flyoverfitness.presentation.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
data class FitnessEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var height: Double,
    var weight: Double,
    var targetWeight: Double
)