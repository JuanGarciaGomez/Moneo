package com.project.jf.moneo.data.local.entity

import ControlPeriodEntity
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = ControlPeriodEntity::class,
            parentColumns = ["id"],
            childColumns = ["controlPeriodId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val controlPeriodId: Long,
    val title: String,
    val amount: Double,
    val date: Long,
    val type: TransactionType,
    val category: String,
    val paymentMethod: String,
    val notes: String? = null
)

