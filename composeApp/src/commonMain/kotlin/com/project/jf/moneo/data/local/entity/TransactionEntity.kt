package com.project.jf.moneo.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.project.jf.moneo.domain.model.Transaction
import com.project.jf.moneo.domain.model.TransactionType

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
    val amount: Double,
    val date: Long,
    val type: TransactionType,
    val category: String,
    val paymentMethod: String,
    val notes: String? = null
)

fun TransactionEntity.toDomain(): Transaction {
    return Transaction(
        id = id,
        controlPeriodId = controlPeriodId,
        amount = amount,
        date = date,
        type = type,
        category = category,
        paymentMethod = paymentMethod,
        notes = notes
    )
}

fun Transaction.toData(): TransactionEntity {
    return TransactionEntity(
        id = id,
        controlPeriodId = controlPeriodId,
        amount = amount,
        date = date,
        type = type,
        category = category,
        paymentMethod = paymentMethod,
        notes = notes
    )
}
