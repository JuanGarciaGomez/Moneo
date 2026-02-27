package com.project.jf.moneo.data.local

import androidx.room.TypeConverter
import com.project.jf.moneo.domain.model.PaymentMethod
import com.project.jf.moneo.domain.model.TransactionCategory
import com.project.jf.moneo.domain.model.TransactionType

class Converters {
    @TypeConverter
    fun fromTransactionType(value: TransactionType): String {
        return value.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }

    @TypeConverter
    fun fromTransactionCategory(value: TransactionCategory): String {
        return value.name
    }

    @TypeConverter
    fun toTransactionCategory(value: String): TransactionCategory {
        return TransactionCategory.valueOf(value)
    }


    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): String {
        return value.name
    }

    @TypeConverter
    fun toPaymentMethod(value: String): PaymentMethod {
        return PaymentMethod.valueOf(value)
    }

}
