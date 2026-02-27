package com.project.jf.moneo.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.project.jf.moneo.domain.model.PaymentMethod
import com.project.jf.moneo.domain.model.Transaction
import com.project.jf.moneo.domain.model.TransactionCategory
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
    @PrimaryKey(autoGenerate = true) val id: Long? = 0,
    val controlPeriodId: Long? = null,
    val amount: Double? = null,
    val date: Long? = null,
    val type: TransactionType? = null,
    val category: TransactionCategory? = null,
    val paymentMethod: PaymentMethod? = null,
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
